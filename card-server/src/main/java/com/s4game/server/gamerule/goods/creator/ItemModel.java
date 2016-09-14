package com.s4game.server.gamerule.goods.creator;

public class ItemModel {
    private String id;
    private String goodsId;
    private int goodsCount;
    private int bind = 0;
    private long expireTime;
    private int slotNum;
    private int rareLevel;
    private int itemLevel;
    private String attributes;
    private boolean isNotice;

    public ItemModel(String goodsId, int goodsCount) {
        this(goodsId, goodsCount, 0, 0L);
    }

    public ItemModel(String goodsId, int goodsCount, int bind, long expireTime) {
        this.goodsId = goodsId;
        this.goodsCount = goodsCount;
        this.bind = bind;
        this.expireTime = expireTime;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsCount() {
        return this.goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public int getSlotNum() {
        return this.slotNum;
    }

    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }

    public int getRareLevel() {
        return this.rareLevel;
    }

    public void setRareLevel(int rareLevel) {
        this.rareLevel = rareLevel;
    }

    public int getItemLevel() {
        return this.itemLevel;
    }

    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public int getBind() {
        return this.bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public boolean isExpired() {
        if (this.expireTime == 0L) {
            return false;
        }
        return System.currentTimeMillis() >= this.expireTime;
    }

    public String getAttributes() {
        return this.attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public ItemModel copyWithCount(int goodsCount) {
        ItemModel localItemModel = new ItemModel(getGoodsId(), goodsCount);
        localItemModel.setId(getId());
        localItemModel.setBind(getBind());
        localItemModel.setExpireTime(getExpireTime());
        localItemModel.setItemLevel(getItemLevel());
        localItemModel.setRareLevel(getRareLevel());
        localItemModel.setSlotNum(getSlotNum());
        localItemModel.setAttributes(getAttributes());
        return localItemModel;
    }

    public boolean isNotice() {
        return this.isNotice;
    }

    public void setNotice(boolean isNotice) {
        this.isNotice = isNotice;
    }
}