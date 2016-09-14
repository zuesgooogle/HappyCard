package com.s4game.server.bus.bag.entity;

import com.s4game.core.data.IEntity;
import com.s4game.server.gamerule.goods.entity.StoreGoods;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public class RoleBagSlot extends StoreGoods implements IEntity {
    
    private static final long serialVersionUID = 1L;
    
    private int orderNum;

    public RoleBagSlot() {
    
    }
    
    public int getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getPirmaryKeyName() {
        return "id";
    }

    public Object getPrimaryKeyValue() {
        return this.id;
    }

    public RoleBagSlot copy() {
        RoleBagSlot roleBagSlot = new RoleBagSlot();
        roleBagSlot.setId(getId());
        roleBagSlot.setSlotNum(getSlotNum());
        roleBagSlot.setUserRoleId(getUserRoleId());
        roleBagSlot.setGoodsId(getGoodsId());
        roleBagSlot.setCount(getCount());
        roleBagSlot.setBind(getBind());
        roleBagSlot.setRareLevel(getRareLevel());
        roleBagSlot.setItemLevel(getItemLevel());
        roleBagSlot.setExpireTime(getExpireTime());
        roleBagSlot.setAttributes(getAttributes());
        return roleBagSlot;
    }

    public RoleBagSlot(StoreGoods storeGoods) {
        setId(storeGoods.getId());
        setSlotNum(storeGoods.getSlotNum());
        setUserRoleId(storeGoods.getUserRoleId());
        setGoodsId(storeGoods.getGoodsId());
        setCount(storeGoods.getCount());
        setBind(storeGoods.getBind());
        setRareLevel(storeGoods.getRareLevel());
        setItemLevel(storeGoods.getItemLevel());
        setExpireTime(storeGoods.getExpireTime());
        setAttributes(storeGoods.getAttributes());
    }
}
