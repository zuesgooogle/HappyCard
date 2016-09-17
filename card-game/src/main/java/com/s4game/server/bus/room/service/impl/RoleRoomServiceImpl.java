package com.s4game.server.bus.room.service.impl;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s4game.server.bus.id.export.IdGenerator;
import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.bus.room.RoomModuleInfo;
import com.s4game.server.bus.room.dao.IRoleRoomDao;
import com.s4game.server.bus.room.entity.RoleRoom;
import com.s4game.server.bus.room.service.IRoleRoomService;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午1:10:25 
*
*/
@Service
public class RoleRoomServiceImpl implements IRoleRoomService {

    private Logger LOG = LogManager.getLogger(getClass());
    
    @Autowired
    private IRoleRoomDao roleRoomDao;
    
    @Autowired
    private IUserRoleService userRoleService;
    
    @Autowired
    private IdGenerator idGenerator;
    
    @Override
    public void online(String roleId) {
        
    }

    @Override
    public RoleRoom createRoom(String roleId, int round, boolean serial, boolean win) {
        RoleWrapper role = userRoleService.getRole(roleId);
        
        RoleRoom room = new RoleRoom();
        room.setId(idGenerator.getId4Module(RoomModuleInfo.MODULE_NAME));
        room.setUserRoleId(roleId);
        room.setNumber((int) (System.currentTimeMillis() / 1000));
        room.setRound(round);
        room.setSerial(serial);
        room.setWin(win);
        room.setLogUpdateTime(new Timestamp(System.currentTimeMillis()));
        
        roleRoomDao.insertDb(room);
        
        return room;
    }

    
}
