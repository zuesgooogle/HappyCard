package com.s4game.server.public_.room.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s4game.core.container.DataContainer;
import com.s4game.core.data.accessor.AccessType;
import com.s4game.core.data.accessor.GlobalIdentity;
import com.s4game.server.bus.map.MapType;
import com.s4game.server.bus.share.constants.BusShareConstant;
import com.s4game.server.bus.stagecontroll.RoleState;
import com.s4game.server.bus.stagecontroll.command.StageControllCommands;
import com.s4game.server.bus.stagecontroll.position.StageCopyPosition;
import com.s4game.server.public_.room.dao.IRoomDao;
import com.s4game.server.public_.room.dao.impl.RoomDaoImpl;
import com.s4game.server.public_.room.entity.Room;
import com.s4game.server.public_.room.service.IRoomService;
import com.s4game.server.public_.swap.PublicMsgSender;
import com.s4game.server.share.log.Log;
import com.s4game.server.stage.room.RoomStage;
import com.s4game.server.stage.room.RoomStageFactory;
import com.s4game.server.stage.service.IStageService;

@Service
public class RoomServiceImpl implements IRoomService {
    
    public Logger LOG = Log.ROOM;
    
    @Autowired
    private IRoomDao roomDao;
    
    @Autowired
    private IStageService stageService;
    
    @Autowired
    private DataContainer dataContainer;
    
    @Autowired
    private PublicMsgSender publicMsgSender;
    
    public RoomDaoImpl getDao() {
        return (RoomDaoImpl) this.roomDao;
    }
    
    @Override
    public Room createRoom(String roleId, int round, boolean serial, boolean win) {
        Room room = new Room();
        room.setUserRoleId(roleId);
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
        RoomStage stage = RoomStageFactory.create(stageId, mapId);
        
        stageService.addStageCopy(stage);
    }
    
    @Override
    public void enterRoom(String roleId, String roomId) {
        RoleState roleState = this.dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);

        StageCopyPosition copyPosition = new StageCopyPosition(roleId, roomId, MapType.ROOM, 0, 0, new Object[]{});
        roleState.setReadyForPosition(copyPosition);
        
        publicMsgSender.send2GsStageControl(StageControllCommands.CHANGE_STAGE, roleId, new Object[]{});
    }
    
}
