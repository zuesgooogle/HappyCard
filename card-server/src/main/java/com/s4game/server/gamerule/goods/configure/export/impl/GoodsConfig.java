package com.s4game.server.gamerule.goods.configure.export.impl;

import com.alibaba.fastjson.JSON;
import com.s4game.core.data.IEntity;

public class GoodsConfig implements IEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private int category;

    private int itemLevel;

    private int rareLevel;

    private int maxStack;

    private int levelReq;

    private String job;

    private boolean useable;

    private String buffId;

    private String costMoney;

    private String cd;

    private int equipType;

    /**
     * 排序优先级
     */
    private int order;

    public GoodsConfig() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getLevelReq() {
        return levelReq;
    }

    public void setLevelReq(int levelReq) {
        this.levelReq = levelReq;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isUseable() {
        return useable;
    }

    public void setUseable(boolean useable) {
        this.useable = useable;
    }

    public String getBuffId() {
        return buffId;
    }

    public void setBuffId(String buffId) {
        this.buffId = buffId;
    }

    public String getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(String costMoney) {
        this.costMoney = costMoney;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public int getEquipType() {
        return equipType;
    }

    public void setEquipType(int equipType) {
        this.equipType = equipType;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String getPirmaryKeyName() {
        return "id";
    }

    @Override
    public Object getPrimaryKeyValue() {
        return getId();
    }

    @Override
    public IEntity copy() {
        return null;
    }
    
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}