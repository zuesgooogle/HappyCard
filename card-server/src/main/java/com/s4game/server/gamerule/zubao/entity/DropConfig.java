package com.s4game.server.gamerule.zubao.entity;

import java.io.Serializable;

public class DropConfig implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String dropId;
    private float rate;
    private int bind;
    private int index;

    public DropConfig() {
    }

    public DropConfig(String paramString, float paramFloat) {
        this.dropId = paramString;
        this.rate = paramFloat;
    }

    public String getDropId() {
        return this.dropId;
    }

    public void setDropId(String paramString) {
        this.dropId = paramString;
    }

    public float getRate() {
        return this.rate;
    }

    public void setRate(float paramFloat) {
        this.rate = paramFloat;
    }

    public int getBind() {
        return this.bind;
    }

    public void setBind(int paramInt) {
        this.bind = paramInt;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int paramInt) {
        this.index = paramInt;
    }
}