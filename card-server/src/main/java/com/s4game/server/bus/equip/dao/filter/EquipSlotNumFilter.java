package com.s4game.server.bus.equip.dao.filter;

import com.s4game.core.data.IQueryFilter;
import com.s4game.server.bus.equip.entity.RoleEquipSlot;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月14日 下午6:54:42
*
*/
public class EquipSlotNumFilter implements IQueryFilter<RoleEquipSlot> {
    
    private int slotNum;
    private boolean found;

    public EquipSlotNumFilter(int slotNum) {
        this.slotNum = slotNum;
    }

    public boolean check(RoleEquipSlot roleEquipSlot) {
        boolean bool = roleEquipSlot.getSlotNum() == this.slotNum;
        if (bool) {
            bool = true;
        }
        return bool;
    }

    public boolean stopped() {
        return this.found;
    }
}
