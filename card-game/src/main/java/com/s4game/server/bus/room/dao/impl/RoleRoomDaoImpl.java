package com.s4game.server.bus.room.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.AccessType;
import com.s4game.core.data.accessor.GlobalIdentity;
import com.s4game.server.bus.room.dao.IRoleRoomDao;
import com.s4game.server.bus.room.entity.RoleRoom;
import com.s4game.server.bus.share.dao.BusAbsCacheDao;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午12:47:13 
*
*/
@Component
public class RoleRoomDaoImpl extends BusAbsCacheDao<RoleRoom> implements IRoleRoomDao {

    @Override
    public RoleRoom initRoom(String roleId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userRoleId", roleId);
        
        List<RoleRoom> list = getRecords(param, GlobalIdentity.get(), AccessType.getDirectDbType());
        if (null == list || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    @Override
    public RoleRoom getRoomFromDb(String id) {
        return load(id, id, AccessType.getDirectDbType());
    }

    @Override
    public boolean updateDb(RoleRoom room) {
        return update(room, room.getId(), AccessType.getDirectDbType()) > 0;
    }

    @Override
    public boolean deleteDb(RoleRoom room) {
        return delete(room.getId(), room.getId(), AccessType.getDirectDbType()) > 0;
    }

    @Override
    public RoleRoom insertDb(RoleRoom room) {
        return (RoleRoom) insert(room, room.getId(), AccessType.getDirectDbType());
    }

}
