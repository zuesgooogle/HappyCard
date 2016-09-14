package com.s4game.server.bus.bag.dao.filter;

import com.s4game.server.bus.bag.entity.RoleBagSlot;
import com.s4game.server.bus.bag.util.BagUtil;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public class GoodsAmountFilter implements IQueryCountFilter<RoleBagSlot> {

    private int needAmount;

    private String goodsId;

    public GoodsAmountFilter(String goodsId, int needAmount) {
        this.needAmount = needAmount;
        this.goodsId = goodsId;
    }

    public boolean check(RoleBagSlot roleBagSlot) {
        if (!BagUtil.isBag(roleBagSlot.getSlotNum())) {
            return false;
        }
        
        if ((this.needAmount > 0) && (roleBagSlot.getGoodsId().equals(this.goodsId))) {
            if ((roleBagSlot.getExpireTime() == 0L)) {
                this.needAmount -= roleBagSlot.getCount();
                return true;
            }

            if (roleBagSlot.getExpireTime() > System.currentTimeMillis()) {
                this.needAmount -= roleBagSlot.getCount();
                return true;
            }
        }
        return false;
    }

    public boolean stopped() {
        return this.needAmount <= 0;
    }

    public boolean isSatisfied() {
        return this.needAmount <= 0;
    }
}