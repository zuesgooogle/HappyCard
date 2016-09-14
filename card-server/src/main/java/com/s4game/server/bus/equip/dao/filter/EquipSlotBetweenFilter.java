package com.s4game.server.bus.equip.dao.filter;

import com.s4game.core.data.IQueryFilter;
import com.s4game.server.bus.equip.entity.RoleEquipSlot;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年8月14日 下午6:54:42
 * 
 */
public class EquipSlotBetweenFilter implements IQueryFilter<RoleEquipSlot> {

    private int minSlot;
    private int maxSlot;

    public EquipSlotBetweenFilter(int minSlot, int maxSlot) {
        this.minSlot = minSlot;
        this.maxSlot = maxSlot;
    }

    public boolean check(RoleEquipSlot roleEquipSlot) {
        return (roleEquipSlot.getSlotNum() >= this.minSlot) && (roleEquipSlot.getSlotNum() <= this.maxSlot);
    }

    public boolean stopped() {
        return false;
    }
}
