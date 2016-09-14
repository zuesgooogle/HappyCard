package com.s4game.server.bus.bag.comparator;

import java.util.Comparator;

import com.s4game.server.bus.bag.entity.RoleBagSlot;

/**
 * order by slotNum asc
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年8月11日 下午3:08:57
 * 
 */
public class BagSlotNumComparator implements Comparator<RoleBagSlot> {

    public int compare(RoleBagSlot roleBagSlot1, RoleBagSlot roleBagSlot2) {
        return roleBagSlot1.getSlotNum() - roleBagSlot2.getSlotNum();
    }
}
