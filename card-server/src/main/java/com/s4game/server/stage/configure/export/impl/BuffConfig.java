package com.s4game.server.stage.configure.export.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.s4game.core.data.IEntity;

public class BuffConfig implements IEntity {

    private static final long serialVersionUID = 1L;

    private String effectId;
    private String effectTypeID;
    private String effectName;
    private int effectLevel;
    private String effectType;
    private int ifBuff;
    private int lastTime;
    private int canbeDispel;
    private int isRmbeffect;
    private List<String> huchi = new ArrayList();
    private List<String> cover = new ArrayList();
    private int maxStack;
    private Map<String, Float> effectPro = new HashMap();
    private int effectState;
    private String specialEffect;
    private int specialEffectInterval;
    private String specialEffectValue;
    private int combat;
    private boolean deadRemove;
    private float expBeilv;
    private String fearBuffCategory;
    private boolean onlyInstantFight;
    private boolean isProtected;
    private boolean canNotRide;
    private boolean canNotFly;
    private boolean canNotJump;

    public String getPirmaryKeyName() {
        return "effectId";
    }

    public String getPrimaryKeyValue() {
        return this.effectId;
    }

    public String getEffectId() {
        return this.effectId;
    }

    void setEffectId(String paramString) {
        this.effectId = paramString;
    }

    public String getEffectTypeID() {
        return this.effectTypeID;
    }

    void setEffectTypeID(String paramString) {
        this.effectTypeID = paramString;
    }

    public String getEffectName() {
        return this.effectName;
    }

    void setEffectName(String paramString) {
        this.effectName = paramString;
    }

    public int getEffectLevel() {
        return this.effectLevel;
    }

    void setEffectLevel(int paramInt) {
        this.effectLevel = paramInt;
    }

    public String getEffectType() {
        return this.effectType;
    }

    void setEffectType(String paramString) {
        this.effectType = paramString;
    }

    public int getIfBuff() {
        return this.ifBuff;
    }

    void setIfBuff(int paramInt) {
        this.ifBuff = paramInt;
    }

    public int getLastTime() {
        return this.lastTime;
    }

    void setLastTime(int paramInt) {
        this.lastTime = paramInt;
    }

    public int getCanbeDispel() {
        return this.canbeDispel;
    }

    void setCanbeDispel(int paramInt) {
        this.canbeDispel = paramInt;
    }

    public int getIsRmbeffect() {
        return this.isRmbeffect;
    }

    void setIsRmbeffect(int paramInt) {
        this.isRmbeffect = paramInt;
    }

    public List<String> getCover() {
        return this.cover;
    }

    void setCover(List<String> paramList) {
        this.cover = paramList;
    }

    public int getMaxStack() {
        return this.maxStack;
    }

    void setMaxStack(int paramInt) {
        this.maxStack = paramInt;
    }

    public Map<String, Float> getEffectPro() {
        return this.effectPro;
    }

    void setEffectPro(Map<String, Float> paramMap) {
        this.effectPro = paramMap;
    }

    public int getEffectState() {
        return this.effectState;
    }

    void setEffectState(int paramInt) {
        this.effectState = paramInt;
    }

    public IEntity copy() {
        BuffConfig localBuffConfig = new BuffConfig();
        localBuffConfig.setEffectId(this.effectId);
        localBuffConfig.setEffectTypeID(this.effectTypeID);
        localBuffConfig.setEffectName(this.effectName);
        localBuffConfig.setEffectLevel(this.effectLevel);
        localBuffConfig.setEffectType(this.effectType);
        localBuffConfig.setIfBuff(this.ifBuff);
        localBuffConfig.setLastTime(this.lastTime);
        localBuffConfig.setCanbeDispel(this.canbeDispel);
        localBuffConfig.setIsRmbeffect(this.isRmbeffect);
        localBuffConfig.setCover(this.cover);
        localBuffConfig.setMaxStack(this.maxStack);
        localBuffConfig.setEffectPro(this.effectPro);
        localBuffConfig.setEffectState(this.effectState);
        return localBuffConfig;
    }

    public List<String> getExcludeBuffCategory() {
        return this.cover;
    }

    public boolean canOverlap() {
        return this.maxStack > 1;
    }

    public List<String> getSpecialEffects() {
        return null;
    }

    public String getSpecialEffect() {
        return this.specialEffect;
    }

    void setSpecialEffect(String paramString) {
        this.specialEffect = paramString;
    }

    public int getSpecialEffectInterval() {
        return this.specialEffectInterval;
    }

    void setSpecialEffectInterval(int paramInt) {
        this.specialEffectInterval = paramInt;
    }

    public String getSpecialEffectValue() {
        return this.specialEffectValue;
    }

    void setSpecialEffectValue(String paramString) {
        this.specialEffectValue = paramString;
    }

    public int getCombat() {
        return this.combat;
    }

    void setCombat(int paramInt) {
        this.combat = paramInt;
    }

    public boolean isDeadRemove() {
        return this.deadRemove;
    }

    void setDeadRemove(boolean paramBoolean) {
        this.deadRemove = paramBoolean;
    }

    public float getExpBeilv() {
        return this.expBeilv;
    }

    void setExpBeilv(float paramFloat) {
        this.expBeilv = paramFloat;
    }

    public String getFearBuffCategory() {
        return this.fearBuffCategory;
    }

    void setFearBuffCategory(String paramString) {
        this.fearBuffCategory = paramString;
    }

    public boolean onlyInstantFight() {
        return this.onlyInstantFight;
    }

    void setOnlyInstantFight(boolean paramBoolean) {
        this.onlyInstantFight = paramBoolean;
    }

    public boolean isProtected() {
        return this.isProtected;
    }

    void setProtected(boolean paramBoolean) {
        this.isProtected = paramBoolean;
    }

    public List<String> getHuchi() {
        return this.huchi;
    }

    void setHuchi(List<String> paramList) {
        this.huchi = paramList;
    }

    void addHuChi(String paramString) {
        if (null == this.huchi) {
            this.huchi = new ArrayList();
        }
        this.huchi.add(paramString);
    }

    public boolean isCanNotRide() {
        return this.canNotRide;
    }

    void setCanNotRide(boolean paramBoolean) {
        this.canNotRide = paramBoolean;
    }

    public boolean isCanNotFly() {
        return this.canNotFly;
    }

    void setCanNotFly(boolean paramBoolean) {
        this.canNotFly = paramBoolean;
    }

    public boolean isCanNotJump() {
        return this.canNotJump;
    }

    void setCanNotJump(boolean paramBoolean) {
        this.canNotJump = paramBoolean;
    }
}