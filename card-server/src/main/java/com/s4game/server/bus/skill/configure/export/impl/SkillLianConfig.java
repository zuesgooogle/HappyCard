package com.s4game.server.bus.skill.configure.export.impl;

import com.s4game.core.data.IEntity;
import com.s4game.server.gamerule.skill.SkillTargetChooseType;

public class SkillLianConfig implements IEntity {
    
    private static final long serialVersionUID = 1L;
    
    private String skillId;
    private int damageType;
    private int chooseType;
    private int length;
    private int rang;
    private int width;
    private int startAngle;
    private int endAngle;
    private SkillTargetChooseType targetType;
    private int maxTarget;
    private String damageFormuleType;
    private float formulea;
    private float formuleb;
    private float formulec;
    private float formuled;
    private float formulee;
    private float formulef;
    private float formuleg;
    private float formuleh;
    private float zuoqiOdds;

    public String getSkillId() {
        return this.skillId;
    }

    public void setSkillId(String paramString) {
        this.skillId = paramString;
    }

    public int getDamageType() {
        return this.damageType;
    }

    public void setDamageType(int paramInt) {
        this.damageType = paramInt;
    }

    public int getChooseType() {
        return this.chooseType;
    }

    public void setChooseType(int paramInt) {
        this.chooseType = paramInt;
    }

    public int getRang() {
        return this.rang;
    }

    public void setRang(int paramInt) {
        this.rang = paramInt;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int paramInt) {
        this.length = paramInt;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int paramInt) {
        this.width = paramInt;
    }

    public int getStartAngle() {
        return this.startAngle;
    }

    public void setStartAngle(int paramInt) {
        this.startAngle = paramInt;
    }

    public int getEndAngle() {
        return this.endAngle;
    }

    public void setEndAngle(int paramInt) {
        this.endAngle = paramInt;
    }

    public SkillTargetChooseType getTargetType() {
        return this.targetType;
    }

    public void setTargetType(SkillTargetChooseType paramSkillTargetChooseType) {
        this.targetType = paramSkillTargetChooseType;
    }

    public String getDamageFormuleType() {
        return this.damageFormuleType;
    }

    public void setDamageFormuleType(String paramString) {
        this.damageFormuleType = paramString;
    }

    public float getFormulea() {
        return this.formulea;
    }

    public void setFormulea(float paramFloat) {
        this.formulea = paramFloat;
    }

    public float getFormuleb() {
        return this.formuleb;
    }

    public void setFormuleb(float paramFloat) {
        this.formuleb = paramFloat;
    }

    public float getFormulec() {
        return this.formulec;
    }

    public void setFormulec(float paramFloat) {
        this.formulec = paramFloat;
    }

    public float getFormuled() {
        return this.formuled;
    }

    public void setFormuled(float paramFloat) {
        this.formuled = paramFloat;
    }

    public float getFormulee() {
        return this.formulee;
    }

    public void setFormulee(float paramFloat) {
        this.formulee = paramFloat;
    }

    public float getFormulef() {
        return this.formulef;
    }

    public void setFormulef(float paramFloat) {
        this.formulef = paramFloat;
    }

    public float getFormuleg() {
        return this.formuleg;
    }

    public void setFormuleg(float paramFloat) {
        this.formuleg = paramFloat;
    }

    public float getFormuleh() {
        return this.formuleh;
    }

    public void setFormuleh(float paramFloat) {
        this.formuleh = paramFloat;
    }

    public int getMaxTarget() {
        return this.maxTarget;
    }

    public void setMaxTarget(int paramInt) {
        this.maxTarget = paramInt;
    }

    public float getZuoqiOdds() {
        return this.zuoqiOdds;
    }

    public void setZuoqiOdds(float paramFloat) {
        this.zuoqiOdds = paramFloat;
    }

    public String getPirmaryKeyName() {
        return "skillId";
    }

    public Object getPrimaryKeyValue() {
        return this.skillId;
    }

    public IEntity copy() {
        return null;
    }
}