package com.s4game.server.stage.configure.export.impl;

import com.s4game.core.data.IEntity;

public class PublicCdConfig implements IEntity {

    private static final long serialVersionUID = 1L;

    public String id;

    public int time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getPirmaryKeyName() {
        return "id";
    }

    public String getPrimaryKeyValue() {
        return getId();
    }

    public IEntity copy() {
        PublicCdConfig publicCdConfig = new PublicCdConfig();
        publicCdConfig.setId(getId());
        publicCdConfig.setTime(getTime());
        return publicCdConfig;
    }
}
