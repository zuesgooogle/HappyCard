package com.s4game.server.bus.room.service.impl;

import static com.s4game.server.bus.stagecontroll.command.StageControllCommands.APPLY_CHANGE_STAGE;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s4game.core.container.DataContainer;
import com.s4game.server.bus.map.MapType;
import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.bus.room.command.RoomCommands;
import com.s4game.server.bus.room.dao.IRoleRoomDao;
import com.s4game.server.bus.room.entity.RoleRoom;
import com.s4game.server.bus.room.output.RoomOutput;
import com.s4game.server.bus.room.service.IRoleRoomService;
import com.s4game.server.bus.share.constants.BusShareConstant;
import com.s4game.server.bus.stagecontroll.RoleState;
import com.s4game.server.bus.stagecontroll.command.StageControllCommands;
import com.s4game.server.bus.stagecontroll.position.StageCopyPosition;
import com.s4game.server.bus.swap.BusMsgSender;
import com.s4game.server.stage.room.RoomStage;
import com.s4game.server.stage.room.RoomStageFactory;
import com.s4game.server.stage.service.IStageService;
import com.s4game.server.stage.swap.StageMsgQueue;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午1:10:25 
*
*/
@Service
public class RoleRoomServiceImpl implements IRoleRoomService {

    private Logger LOG = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private IRoleRoomDao roleRoomDao;
    
    @Autowired
    private IUserRoleService userRoleService;
    
    @Autowired
    private IStageService stageService;
    
    @Autowired
    private DataContainer dataContainer;
    
    @Autowired
    private BusMsgSender busMsgSender;
    
    
    
    @Override
    public void online(String roleId) {
        RoleWrapper role = userRoleService.getRole(roleId);
        
        RoleRoom room = roleRoomDao.cacheLoad(roleId, roleId);
        if (room != null) {
            busMsgSender.send2One(RoomCommands.CREATE_ROOM, roleId, RoomOutput.room(room));
        }
    }

    @Override
    public RoleRoom createRoom(String roleId, int round, boolean serial, boolean win) {
        RoleWrapper role = userRoleService.getRole(roleId);
        
        RoleRoom room = new RoleRoom();
        room.setUserRoleId(roleId);
        room.setNumber((int) (System.currentTimeMillis() / 1000));
        room.setRound(round);
        room.setSerial(serial);
        room.setWin(win);
        room.setLogUpdateTime(new Timestamp(System.currentTimeMillis()));
        
        roleRoomDao.insertDb(room);
        
        return room;
    }

    @Override
    public void createRoomCopy(String roleId, String stageId, String mapId,
            Object[] additionalData) {
        RoomStage stage = RoomStageFactory.create(stageId, mapId);
        
        stageService.addStageCopy(stage);
    }

    @Override
    public void enterRoom(String roleId, String roomId) {
        RoleState roleState = this.dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);

        StageCopyPosition copyPosition = new StageCopyPosition(roleId, roomId, MapType.ROOM, 0, 0, new Object[]{});
        roleState.setReadyForPosition(copyPosition);
        
        busMsgSender.send2BusInner(StageControllCommands.CHANGE_STAGE, roleId, new Object[]{});
    }
    
}
