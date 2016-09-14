package com.s4game.server.stage.configure.export.impl;

public class MoneyDropRule {
    private int dropCount;
    private int dropJb;
    private boolean calShuaiJian;
    private float dropRate;

    public int getDropCount() {
        return this.dropCount;
    }

    void setDropCount(int paramInt) {
        this.dropCount = paramInt;
    }

    public int getDropJb() {
        return this.dropJb;
    }

    void setDropJb(int paramInt) {
        this.dropJb = paramInt;
    }

    public MoneyDropRule(int paramInt1, int paramInt2) {
        this.dropCount = paramInt1;
        this.dropJb = paramInt2;
    }

    public MoneyDropRule() {
    }

    public boolean calShuaiJian() {
        return this.calShuaiJian;
    }

    void setCalShuaiJian(boolean paramBoolean) {
        this.calShuaiJian = paramBoolean;
    }

    void setDropRate(float paramFloat) {
        this.dropRate = paramFloat;
    }

    public float getDropRate() {
        return this.dropRate;
    }
}