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
public class GoodsIdFilter implements IQueryFilter<RoleBagSlot> {
    
    private String goodsId;

    public GoodsIdFilter(String goodsId) {
        this.goodsId = goodsId;
    }

    public boolean check(RoleBagSlot roleBagSlot) {
        if (!BagUtil.isBag(roleBagSlot.getSlotNum())) {
            return false;
        }
        
        if (this.goodsId.equals(roleBagSlot.getGoodsId())) {
            return ( roleBagSlot.getExpireTime() == 0L || roleBagSlot.getExpireTime() > System.currentTimeMillis());
        }
        return false;
    }

    public boolean stopped() {
        return false;
    }
}
