package com.s4game.server.bus.equip.service;

import com.s4game.server.bus.equip.entity.RoleEquipSlot;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月14日 下午6:53:01
 *
 */

public interface IRoleEquipSlotService {

	/**
	 * 穿戴（取下）装备
	 * 
	 * @param roleId
	 * @param guid
	 * @param bagSlot	背包格 index
	 * @param equipSlot 装备位 index
	 * @return
	 */
    public Object[] dressEquip(String roleId, String guid, int bagSlot, int equipSlot);
    
    /**
     * 佩戴装备
     * 
     * @param roleId
     * @param guid
     * @param bagSlot
     * @param equipSlot
     * @return
     */
    public Object[] loadEquip(String roleId, String guid, int bagSlot, int equipSlot);
    
    /**
     * 取下装备
     * 
     * @param roleId
     * @param guid
     * @param equipSlot
     * @param bagSlot
     * @return
     */
    public Object[] unloadEquip(String roleId, String guid, int equipSlot, int bagSlot);
    
    /**
     * 获取装备信息，根据 slot num
     * 
     * @param roleId
     * @param slotNum
     * @return
     */
    public RoleEquipSlot getRoleEquipSlot(String roleId, int slotNum);
}
