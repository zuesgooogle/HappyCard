package com.s4game.server.gamerule.goods.entity;

import com.s4game.core.data.AbsVersion;

public class StoreGoods extends AbsVersion {

    protected String id;
    private String userRoleId;
    private int slotNum;
    private String goodsId;
    private int count;
    private int bind;
    private long expireTime;
    private int itemLevel;
    private int rareLevel;
    private String attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public int getRareLevel() {
        return rareLevel;
    }

    public void setRareLevel(int rareLevel) {
        this.rareLevel = rareLevel;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public boolean isExpired() {
        if (this.expireTime == 0L) {
            return false;
        }
        return System.currentTimeMillis() >= this.expireTime;
    }

}