package com.s4game.server.bus.bag.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.AccessType;
import com.s4game.server.bus.bag.dao.IRoleBagSlotDao;
import com.s4game.server.bus.bag.entity.RoleBagSlot;
import com.s4game.server.bus.share.dao.BusAbsCacheDao;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月11日 下午3:02:52
 *
 */
@Component
public class RoleBagSlotDaoImpl extends BusAbsCacheDao<RoleBagSlot> implements IRoleBagSlotDao {

    @Override
    public RoleBagSlot selectDb(String roleId, String id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("userRoleId", roleId);
        
        List<RoleBagSlot> list = getRecords(params, roleId, AccessType.getDirectDbType());
        if( null != list && !list.isEmpty() ) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean updateDb(RoleBagSlot bagSlot) {
        return update(bagSlot, bagSlot.getUserRoleId(), AccessType.getDirectDbType()) > 0;
    }

    @Override
    public boolean deleteDb(String roleId, String id) {
        return delete(id, roleId, AccessType.getDirectDbType()) > 0;
    }

    @Override
    public void insertDb(RoleBagSlot bagSlot) {
        insert(bagSlot, bagSlot.getUserRoleId(), AccessType.getDirectDbType());
    }

}
