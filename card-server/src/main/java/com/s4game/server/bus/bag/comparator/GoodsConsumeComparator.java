package com.s4game.server.bus.bag.comparator;

import java.util.Comparator;

import com.s4game.server.bus.bag.entity.RoleBagSlot;
import com.s4game.server.bus.bag.util.BagUtil;

public class GoodsConsumeComparator implements Comparator<RoleBagSlot> {
    
    public int compare(RoleBagSlot roleBagSlot1, RoleBagSlot roleBagSlot2) {
        Long localLong1 = roleBagSlot1.getExpireTime();
        Long localLong2 = roleBagSlot2.getExpireTime();
        
        localLong1 = Long.valueOf(localLong1 == null ? 0L : localLong1.longValue());
        localLong2 = Long.valueOf(localLong2 == null ? 0L : localLong2.longValue());
        
        if (BagUtil.compareExpireTime(localLong1, localLong2)) {
            return roleBagSlot2.getBind() - roleBagSlot1.getBind();
        }
        
        if (localLong1.longValue() == 0L) {
            return 1;
        }
        
        if (localLong2.longValue() == 0L) {
            return -1;
        }
        
        return (int) (localLong1.longValue() - localLong2.longValue());
    }
}
