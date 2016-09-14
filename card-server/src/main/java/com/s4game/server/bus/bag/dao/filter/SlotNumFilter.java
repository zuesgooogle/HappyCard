package com.s4game.server.bus.bag.dao.filter;

import com.s4game.core.data.IQueryFilter;
import com.s4game.server.bus.bag.entity.RoleBagSlot;
import com.s4game.server.bus.bag.util.BagUtil;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public class SlotNumFilter implements IQueryFilter<RoleBagSlot> {
    
    private int slotNum;
    
    private boolean found;

    public SlotNumFilter(int slotNum) {
        this.slotNum = slotNum;
    }

    public boolean check(RoleBagSlot roleBagSlot) {
        if (!BagUtil.isBag(roleBagSlot.getSlotNum())) {
            return false;
        }
        
        boolean bool = roleBagSlot.getSlotNum() == this.slotNum;
        if (bool) {
            this.found = true;
        }
        return bool;
    }

    public boolean stopped() {
        return this.found;
    }
}