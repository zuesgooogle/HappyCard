package com.s4game.server.bus.bag.comparator;

import java.util.Comparator;

import com.s4game.server.bus.bag.entity.RoleBagSlot;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public class GoodsIdComparator implements Comparator<RoleBagSlot> {
    
    public int compare(RoleBagSlot roleBagSlot1, RoleBagSlot roleBagSlot2) {
        if (roleBagSlot1.getGoodsId().compareTo(roleBagSlot2.getGoodsId()) > 0) {
            return 1;
        }
        if (roleBagSlot1.getGoodsId().compareTo(roleBagSlot2.getGoodsId()) == 0) {
            return 0;
        }
        return -1;
    }
}