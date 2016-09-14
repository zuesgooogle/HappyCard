package com.s4game.server.bus.equip.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.s4game.core.data.IEntity;
import com.s4game.server.gamerule.goods.entity.StoreGoods;

public class RoleEquipSlot extends StoreGoods implements Serializable, IEntity {

    private static final long serialVersionUID = 1L;

    private static final Timestamp CURRENT_TIMESTAMP = null;
    
    private Timestamp logUpdateTime = CURRENT_TIMESTAMP;

    public RoleEquipSlot() {
        
    }

    public RoleEquipSlot(StoreGoods storeGoods) {
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
    
    public String getPirmaryKeyName() {
        return "id";
    }

    public String getPrimaryKeyValue() {
        return getId();
    }

    
    @Override
    public RoleEquipSlot copy() {
        RoleEquipSlot roleEquipSlot = new RoleEquipSlot();
        roleEquipSlot.setId(getId());
        roleEquipSlot.setSlotNum(getSlotNum());
        roleEquipSlot.setUserRoleId(getUserRoleId());
        roleEquipSlot.setGoodsId(getGoodsId());
        roleEquipSlot.setCount(getCount());
        roleEquipSlot.setBind(getBind());
        roleEquipSlot.setExpireTime(getExpireTime());
        roleEquipSlot.setRareLevel(getRareLevel());
        roleEquipSlot.setItemLevel(getItemLevel());
        roleEquipSlot.setAttributes(getAttributes());
        return roleEquipSlot;
    }

}
