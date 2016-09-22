package com.s4game.server.public_.room.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s4game.core.container.DataContainer;
import com.s4game.core.data.accessor.AccessType;
import com.s4game.core.data.accessor.GlobalIdentity;
import com.s4game.server.bus.map.MapType;
import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.bus.share.constants.BusShareConstant;
import com.s4game.server.bus.stagecontroll.RoleState;
import com.s4game.server.bus.stagecontroll.command.StageControllCommands;
import com.s4game.server.bus.stagecontroll.position.StageCopyPosition;
import com.s4game.server.public_.room.RoomConstants;
import com.s4game.server.public_.room.command.RoomCommands;
import com.s4game.server.public_.room.dao.IRoomDao;
import com.s4game.server.public_.room.dao.impl.RoomDaoImpl;
import com.s4game.server.public_.room.entity.Room;
import com.s4game.server.public_.room.model.RoomBusinessData;
import com.s4game.server.public_.room.model.RoomMemberData;
import com.s4game.server.public_.room.model.RoomStatus;
import com.s4game.server.public_.room.output.RoomOutput;
import com.s4game.server.public_.room.service.IRoomService;
import com.s4game.server.public_.swap.PublicMsgSender;
import com.s4game.server.share.log.Log;
import com.s4game.server.stage.model.core.stage.ElementType;
import com.s4game.server.stage.model.element.role.Role;
import com.s4game.server.stage.room.RoomStage;
import com.s4game.server.stage.service.IStageService;
import com.s4game.server.stage.swap.StageMsgSender;

@Service
public class RoomServiceImpl implements IRoomService {
    
    public Logger LOG = Log.ROOM;
    
    @Autowired
    private IRoomDao roomDao;
    
    @Autowired
    private IStageService stageService;
    
    @Autowired
    private IUserRoleService roleService;
    
    @Autowired
    private DataContainer dataContainer;
    
    @Autowired
    private PublicMsgSender publicMsgSender;
    
    @Autowired
    private StageMsgSender stageMsgSender;
    
    public RoomDaoImpl getDao() {
        return (RoomDaoImpl) this.roomDao;
    }
    
    @Override
    public List<Room> loadAll() {
        return getDao().dbLoadAll();
    }
    
    @Override
    public Room loadById(long roomId) {
        return getDao().dbLoad(roomId);
    }
    
    @Override
    public Room loadByRoleId(String roleId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userRoleId", roleId);
        
        List<Room> list = getDao().getRecords(param);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public Room createRoom(String roleId, int round, boolean serial, boolean win) {
        Room room = loadByRoleId(roleId);
        if (room != null) {
            return room;
        }
        
        room = new Room();
        room.setUserRoleId(roleId);
        room.setStatus(RoomStatus.PREPARE);
        room.setId((int) (System.currentTimeMillis() / 1000));
        room.setMaxRound(round);
        room.setSerial(serial);
        room.setWin(win);
        
        Timestamp now = new Timestamp(System.currentTimeMillis());
        room.setCreateTime(now);
        room.setLogUpdateTime(now);
        
        getDao().insert(room, GlobalIdentity.get(), AccessType.getDirectDbType());
        
        return room;
    }

    @Override
    public void createRoomCopy(String roleId, String stageId, String mapId,
            Object[] additionalData) {
//        RoomStage stage = RoomStageFactory.create(stageId, mapId);
//        
//        stageService.addStageCopy(stage);
    }
    
    @Override
    public void enterRoom(String roleId, String roomId) {
        RoomStage stage = stageService.getStage(roomId);
        if (stage == null) {
            LOG.error("roleId: {} enter room failed. room: {} not exist.", roleId, roomId);
            return;
        }
        
        //房间是否已满
        RoomBusinessData data = stage.getRoomBusinessData();
        if (data.getMembers().size() >= RoomConstants.MEMBER_SIZE) {
            LOG.info("room: {} is full.", roomId);
            return;
        }
        
        RoleState roleState = this.dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);

        StageCopyPosition copyPosition = new StageCopyPosition(roleId, roomId, MapType.ROOM, 0, 0, new Object[]{});
        roleState.setReadyForPosition(copyPosition);
        
        publicMsgSender.send2GsStageControl(StageControllCommands.CHANGE_STAGE, roleId, new Object[]{});
    }

    @Override
    public void roleEnterStageHandle(String stageId, String roleId) {
        RoomStage stage = stageService.getStage(stageId);
        
        RoleWrapper role = roleService.getRole(roleId);
        
        RoomBusinessData businessData = stage.getRoomBusinessData();
        RoomMemberData memberData = businessData.findMemberData(roleId);
        if (memberData == null) {
            memberData = new RoomMemberData(role);
        }
        memberData.setReady(true);
        businessData.getMembers().add(memberData);
        
        //广播给房间玩家上线
        Collection<Role> roles = stage.getElementsByType(ElementType.ROLE);
        List<String> roleIds = new ArrayList<>(roles.size());
        for (Role r : roles) {
            roleIds.add(r.getId());
        }
        
        stageMsgSender.sned2Many(RoomCommands.JOIN_ROOM, roleId, stageId, roleIds.toArray(new String[0]), RoomOutput.join(memberData));
    }
    
}
