package com.s4game.server.bus.stagecontroll.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.s4game.core.container.DataContainer;
import com.s4game.server.bus.role.export.IRoleExportService;
import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.share.constants.BusShareConstant;
import com.s4game.server.bus.stagecontroll.RoleState;
import com.s4game.server.bus.stagecontroll.command.StageControllCommands;
import com.s4game.server.bus.stagecontroll.output.StageControllOutput;
import com.s4game.server.bus.stagecontroll.position.AbsRolePosition;
import com.s4game.server.bus.stagecontroll.position.RoleLocation;
import com.s4game.server.bus.stagecontroll.position.RoleNormalPosition;
import com.s4game.server.bus.stagecontroll.position.StageCopyPosition;
import com.s4game.server.bus.stagecontroll.service.IStageControllService;
import com.s4game.server.bus.swap.BusMsgSender;
import com.s4game.server.stage.entity.RoleStage;
import com.s4game.server.stage.export.RoleStageWrapper;
import com.s4game.server.stage.model.core.stage.Point;
import com.s4game.server.stage.service.IRoleStageService;
import com.s4game.server.stage.service.IStageService;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月2日 下午5:02:30
 * 
 */
@Component
public class StageControllServiceImpl implements IStageControllService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Resource
    private IRoleExportService roleExportService;

    @Resource
    private IStageService stageService;

    @Resource
    private IRoleStageService roleStageService;

    @Resource
    private DataContainer dataContainer;

    @Resource
    private BusMsgSender busMsgSender;

    @Override
    public Object login(String roleId) {
        RoleStage roleStage = roleStageService.loadRoleStage(roleId);
        RoleStageWrapper roleStageWrapper = new RoleStageWrapper(roleStage);

        loginHandler(roleStageWrapper, roleId);
        RoleWrapper roleWrapper = this.roleExportService.getRole(roleId);

        int vipLevel = 1;
        Object[] chargeInfo = new Object[] { 1000, 50 };
        int gmState = 0;

        return StageControllOutput.login(roleWrapper, roleStageWrapper, vipLevel, chargeInfo, gmState);
    }

    /**
     * 初始化角色坐标，所在地图，场景
     * 
     * @param stageWrapper
     * @param roleId
     */
    private void loginHandler(RoleStageWrapper stageWrapper, String roleId) {
        RoleState roleState = new RoleState(roleId);
        /**
         * 缓存用户状态数据
         */
        dataContainer.putData(BusShareConstant.COMPONENT_NAME, roleId, roleState);

        RoleNormalPosition roleNormalPosition = new RoleNormalPosition(stageWrapper.getUserRoleId(), stageWrapper.getMapId(), stageWrapper.getMapX(), stageWrapper.getMapY());

        /**
         * 离线坐标 mapId, stageId, x, y
         */
        Object[] offlinePosition = stageWrapper.getCopyInfo();
        if (null != offlinePosition) {
            String stageId = (String) offlinePosition[1];

            StageCopyPosition stagePosition = new StageCopyPosition(roleId, (String) offlinePosition[0], (Integer) offlinePosition[2],
                    (Integer) offlinePosition[4], stageId, null);
            stagePosition.setStageExist(true);
            if (stageService.stageCanEnter(stageId)) {
                roleState.setReadyForPosition(stagePosition);
                roleState.setOfflineSavePosition(roleNormalPosition);
            } else {
                roleState.setReadyForPosition(roleNormalPosition);
            }
        } else {
            roleState.setReadyForPosition(roleNormalPosition);
        }
    }

    @Override
    public Object[] applyChangeMapAfterLogin(String roleId) {
        RoleState roleState = dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);
        if (null == roleState) {
            return null;
        }

        AbsRolePosition rolePosition = roleState.getReadyToPosition();
        return StageControllOutput.applyChangeMap(rolePosition.getMapId(), rolePosition.getX(), rolePosition.getY());
    }

    @Override
    public Object logout(String roleId) {
        RoleState roleState = dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);
        if (null == roleState) {
            return null;
        }

        AbsRolePosition rolePosition = roleState.getCurPosition();
        if (null == rolePosition) {
            LOG.error("roleId: {} not in stage.", roleId);
            return null;
        }

        String stageId = rolePosition.getStageId();
        if (null == stageId) {
            LOG.error("roleId: {} not in stage.", roleId);
            return null;
        }

        roleStageService.syncRoleStageData(roleId, stageId);

        leaveStage(rolePosition);

        return null;
    }

    @Override
    public void changeMap(String roleId) {
        RoleState roleState = dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);
        if (null == roleState) {
            return;
        }

        // 退出当前场景
        AbsRolePosition curPosition = roleState.getCurPosition();
        if (null != curPosition) {
            Point point = stageService.getPosition(curPosition.getStageId(), roleId);
            if (null != point) {
                curPosition.setPosition(point.getX(), point.getY());
            }

            roleStageService.syncRoleStageData(roleId, curPosition.getStageId());
            leaveStage(curPosition);
        }

        // 使用 curPosition = readPosition
        AbsRolePosition readyPosition = roleState.getReadyToPosition();
        checkStageAndEnter(readyPosition);

        roleState.setReadyForPosition(null);
        roleState.setCurPosition(readyPosition);

        // 离线存储坐标
        if (true) {
            RoleNormalPosition offlinePosition = new RoleNormalPosition(roleId, readyPosition.getMapId(), readyPosition.getX(), readyPosition.getY());
            roleState.setOfflineSavePosition(offlinePosition);
        }
    }

    private void checkStageAndEnter(AbsRolePosition rolePosition) {
        if (rolePosition.isCopyMap()) {
            StageCopyPosition copyPosition = (StageCopyPosition) rolePosition;

            // 副本不存在，创建新副本
            if (!copyPosition.isStageExist()) {
                Object[] additionalData = copyPosition.getAdditionalData();

            }
        } else {
            this.stageService.checkAndCreateStage(rolePosition.getStageId(), rolePosition.getMapId());
        }

        enterStage(rolePosition);
    }

    private void leaveStage(AbsRolePosition rolePosition) {
        busMsgSender.send2Stage(StageControllCommands.INNER_LEAVE_STAGE, rolePosition.getRoleId(), new Object[] { rolePosition.getStageId() });
    }

    private void enterStage(AbsRolePosition rolePosition) {
        busMsgSender.send2Stage(StageControllCommands.INNER_ENTER_STAGE, rolePosition.getRoleId(), rolePosition.enterPositionFormat());
    }

    @Override
    public boolean isInCopy(String roleId) {
        RoleState roleState = dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);
        if (null == roleState) {
            return false;
        }

        AbsRolePosition rolePosition = roleState.getCurPosition();
        if (null == rolePosition) {
            return false;
        }

//        return MapType.isCopy(rolePosition.getMapType());
        return true;
    }

    @Override
    public boolean isOnline(String roleId) {
        RoleState roleState = dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);
        if (null == roleState) {
            return false;
        }

        return roleState.getCurPosition() != null;
    }

    @Override
    public void serverStartInitStage() {
        
    }

    @Override
    public AbsRolePosition getOfflineSaveMapPosition(String roleId) {
        RoleState roleState = dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);
        if (null != roleState) {
            return roleState.getOfflineSavePosition();
        }
        return null;
    }
    
    @Override
    public RoleLocation getHisMapPosition(String roleId) {
        RoleState roleState = dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);
        if (null == roleState) {
            return null;
        }
        
        AbsRolePosition rolePosition = roleState.getCurPosition();
        if( null == rolePosition ) {
            rolePosition = roleState.getOfflineSavePosition();
        }
        
        return new RoleLocation(rolePosition.getMapId(), rolePosition.getStageId(), rolePosition.getX(), rolePosition.getY());
    }

    @Override
    public Object applyChangeCopy(String roleId, String mapId, int x, int y, Object[] additionalData) {
        RoleState roleState = this.dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);
        if (null != roleState.getReadyToPosition()) {
            return null;
        }

        StageCopyPosition copyPosition = new StageCopyPosition(roleId, mapId, x, y, additionalData);

        roleState.setReadyForPosition(copyPosition);

        return StageControllOutput.applyChangeMap(mapId, x, y);
    }

}
