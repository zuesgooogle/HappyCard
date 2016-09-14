package com.s4game.server.bus.equip.dao;

import com.s4game.core.data.accessor.dao.ICacheInitDaoOperation;
import com.s4game.server.bus.equip.entity.RoleEquipSlot;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年8月14日 下午6:45:38
 * 
 */

public interface IRoleEquipSlotDao extends ICacheInitDaoOperation<RoleEquipSlot> {

    public boolean updateDb(RoleEquipSlot roleEquipSlot);

    public boolean deleteDb(String roleId, String id);

    public RoleEquipSlot selectDb(String roleId, String id);

    public RoleEquipSlot getRoleEquipSlotFromDb(String roleId, int slotNum);

}
