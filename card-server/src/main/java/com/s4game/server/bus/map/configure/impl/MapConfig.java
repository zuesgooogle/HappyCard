package com.s4game.server.bus.map.configure.impl;

import com.s4game.core.data.IEntity;
import com.s4game.server.bus.map.MapType;
import com.s4game.server.stage.model.core.stage.Point;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月17日 上午10:15:50
 * 
 */

public class MapConfig implements IEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private int mayType;

    private int minLevel;

    private int maxLevel;

    private Point fly;

    /**
     * 进入场景Buff
     */
    private String bornBuff;

    /**
     * 死亡复活Buff
     */
    private String reviveBuff;

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

    public int getMayType() {
        return mayType;
    }

    public void setMayType(int mayType) {
        this.mayType = mayType;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Point getFly() {
        return fly;
    }

    public void setFly(Point fly) {
        this.fly = fly;
    }

    public String getBornBuff() {
        return bornBuff;
    }

    public void setBornBuff(String bornBuff) {
        this.bornBuff = bornBuff;
    }

    public String getReviveBuff() {
        return reviveBuff;
    }

    public void setReviveBuff(String reviveBuff) {
        this.reviveBuff = reviveBuff;
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

}
