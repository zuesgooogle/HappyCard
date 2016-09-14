package com.s4game.server.bus.equip.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.AccessType;
import com.s4game.server.bus.equip.dao.IRoleEquipSlotDao;
import com.s4game.server.bus.equip.entity.RoleEquipSlot;
import com.s4game.server.bus.share.dao.BusAbsCacheDao;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月14日 下午6:48:06
 *
 */
@Component
public class RoleEquipSlotDaoImpl extends BusAbsCacheDao<RoleEquipSlot> implements IRoleEquipSlotDao {

    @Override
    public boolean updateDb(RoleEquipSlot roleEquipSlot) {
        return update(roleEquipSlot, roleEquipSlot.getUserRoleId(), AccessType.getDirectDbType()) > 0;
    }

    @Override
    public boolean deleteDb(String roleId, String id) {
        return delete(id, roleId, AccessType.getDirectDbType()) > 0;
    }

    @Override
    public RoleEquipSlot selectDb(String roleId, String id) {
        return load(id, roleId, AccessType.getDirectDbType());
    }

    @Override
    public RoleEquipSlot getRoleEquipSlotFromDb(String roleId, int slotNum) {
        Map param = new HashMap();
        param.put("userRoleId", roleId);
        param.put("slotNum", slotNum);
        
        List<RoleEquipSlot> list = getRecords(param, roleId, AccessType.getDirectDbType());
        if( null == list || list.isEmpty() ) {
            return null;
        }
        
        return list.get(0);
    }
    
}
