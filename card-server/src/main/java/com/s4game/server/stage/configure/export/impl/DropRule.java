package com.s4game.server.stage.configure.export.impl;

import com.s4game.server.stage.configure.constants.DropIdType;
import com.s4game.server.stage.configure.constants.DropRateType;
import com.s4game.server.stage.configure.constants.DropTriggerType;

public class DropRule {
    private DropIdType dropIdType;
    private String dropId;
    private String componentDropId;
    private float dropRate;
    private boolean isBind;
    private float bindRate;
    private boolean isPickProtect;
    private DropTriggerType dropTriggerType;
    private String taskId;
    private DropRateType dropRateType;
    private int dropPeriodCount;
    private int dropCount;
    private float dropRateDownFactor;
    private float dropRateDownMin;

    public String getTaskId() {
        return this.taskId;
    }

    void setTaskId(String paramString) {
        this.taskId = paramString;
    }

    void setPickProtect(boolean paramBoolean) {
        this.isPickProtect = paramBoolean;
    }

    void setDropTriggerType(DropTriggerType paramDropTriggerType) {
        this.dropTriggerType = paramDropTriggerType;
    }

    void setDropIdType(DropIdType paramDropIdType) {
        this.dropIdType = paramDropIdType;
    }

    void setDropId(String paramString) {
        this.dropId = paramString;
    }

    void setComponentDropId(String paramString) {
        this.componentDropId = paramString;
    }

    void setDropRate(float paramFloat) {
        this.dropRate = paramFloat;
    }

    void setBind(boolean paramBoolean) {
        this.isBind = paramBoolean;
    }

    void setDropRateType(DropRateType paramDropRateType) {
        this.dropRateType = paramDropRateType;
    }

    void setDropPeriodCount(int paramInt) {
        this.dropPeriodCount = paramInt;
    }

    void setDropCount(int paramInt) {
        this.dropCount = paramInt;
    }

    public DropTriggerType getDropTriggerType() {
        if (this.dropTriggerType == null) {
            return DropTriggerType.NORMAL;
        }
        return this.dropTriggerType;
    }

    public DropRateType getDropRateType() {
        if (this.dropRateType == null) {
            return DropRateType.LOTTERY;
        }
        return this.dropRateType;
    }

    public float getDropRate() {
        return this.dropRate;
    }

    public int getDropPeriodCount() {
        return this.dropPeriodCount;
    }

    public DropIdType getDropIdType() {
        return this.dropIdType;
    }

    public String getDropId() {
        return this.dropId;
    }

    public String getComponentDropId() {
        return this.componentDropId;
    }

    public int getDropCount() {
        return this.dropCount;
    }

    public boolean isBind() {
        return this.isBind;
    }

    public boolean isPickProtect() {
        return this.isPickProtect;
    }

    public float getDropRateDownFactor() {
        return this.dropRateDownFactor;
    }

    void setDropRateDownFactor(float paramFloat) {
        this.dropRateDownFactor = paramFloat;
    }

    public float getDropRateDownMin() {
        return this.dropRateDownMin;
    }

    void setDropRateDownMin(float paramFloat) {
        this.dropRateDownMin = paramFloat;
    }

    public float getBindRate() {
        return this.bindRate;
    }

    void setBindRate(float paramFloat) {
        this.bindRate = paramFloat;
    }
}

/*
 * Location: D:\jd-gui\dntg_start.jar
 * 
 * Qualified Name: com.xianling.stage.configure.export.impl.DropRule
 * 
 * JD-Core Version: 0.7.0.1
 */