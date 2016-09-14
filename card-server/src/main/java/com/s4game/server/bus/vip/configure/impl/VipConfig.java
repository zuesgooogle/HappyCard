package com.s4game.server.bus.vip.configure.impl;

import com.s4game.core.data.IEntity;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月3日 上午11:56:51
 * 
 */

public class VipConfig implements IEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer level;

    private String name;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPirmaryKeyName() {
        return "level";
    }

    @Override
    public Object getPrimaryKeyValue() {
        return getLevel();
    }

    @Override
    public IEntity copy() {
        return null;
    }

}
