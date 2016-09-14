package com.s4game.server.bus.skill.configure.export.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.s4game.core.data.IEntity;
import com.s4game.server.gamerule.skill.SkillFirePathType;
import com.s4game.server.gamerule.skill.SkillTargetChooseType;
import com.s4game.server.gamerule.skill.SkillType;

public class SkillConfig implements IEntity {
    
    private static final long serialVersionUID = 1L;
    
    private String primary;
    private String name;
    private String skillCategory;
    private String skillId;
    private int skillLevel;
    private int activeOrPassive;
    private SkillTargetChooseType targetType;
    private int damageType;
    private int consumeType;
    private Float consume1;
    private Float consume2;
    private String cd1;
    private String cd2;
    private int length;
    private int rang;
    private boolean isRang;
    private int maxTarget;
    private int maxHurt;
    private boolean voltCalculate;
    private boolean criticalCalculate;
    private float formulea;
    private float formuleb;
    private float formulec;
    private float formuled;
    private float formulee;
    private float formulef;
    private float formuleg;
    private float formuleh;
    private int startAngle;
    private int endAngle;
    private List<SkillEffectConfig> effectConfigs = new ArrayList();
    private float baseTohit;
    private int baseCritical;
    private int baseDodgeDefance;
    private String damageFormuleType;
    private float baodi;
    private String harmEffect;
    private Integer casttime;
    private Integer damageDelay;
    private boolean rebound;
    private Integer addVigour;
    private float hatredN1;
    private float hatredN2;
    private Integer hatredType;
    private Map<String, Float> attributes;
    private SkillType skillType;
    private float skillTriggerOdds;
    private String skillTriggerFurmula;
    private String[] lianSkills;
    private float zuoqiSkillBookOdds;
    private SkillFirePathType firePathType;
    private boolean isLastingSkill;
    private int waveNum;
    private boolean callSkill;
    private String[] callMonsters;
    private float callAttributeOdds;
    private int callLifeTime;
    private boolean isShenQiSkill;
    private boolean ispetskill;
    private float resistSkillRate;
    private float resistBuffRate;

    public boolean isShenQiSkill() {
        return this.isShenQiSkill;
    }

    public void setShenQiSkill(boolean paramBoolean) {
        this.isShenQiSkill = paramBoolean;
    }

    public String getName() {
        return this.name;
    }

    public String getHarmEffect() {
        return this.harmEffect;
    }

    void setHarmEffect(String paramString) {
        this.harmEffect = paramString;
    }

    void setName(String paramString) {
        this.name = paramString;
    }

    public String getSkillCategory() {
        return this.skillCategory;
    }

    void setSkillCategory(String paramString) {
        this.skillCategory = paramString;
    }

    public String getSkillId() {
        return this.skillId;
    }

    void setSkillId(String paramString) {
        this.skillId = paramString;
        this.primary = paramString;
    }

    public int getSkillLevel() {
        return this.skillLevel;
    }

    void setSkillLevel(int paramInt) {
        this.skillLevel = paramInt;
    }

    public int getActiveOrPassive() {
        return this.activeOrPassive;
    }

    void setActiveOrPassive(int paramInt) {
        this.activeOrPassive = paramInt;
    }

    public int getConsumeType() {
        return this.consumeType;
    }

    void setConsumeType(int paramInt) {
        this.consumeType = paramInt;
    }

    public Float getConsume1() {
        return this.consume1;
    }

    void setConsume1(Float paramFloat) {
        this.consume1 = paramFloat;
    }

    public Float getConsume2() {
        return this.consume2;
    }

    void setConsume2(Float paramFloat) {
        this.consume2 = paramFloat;
    }

    public String getCd1() {
        return this.cd1;
    }

    void setCd1(String paramString) {
        this.cd1 = paramString;
    }

    public String getCd2() {
        return this.cd2;
    }

    void setCd2(String paramString) {
        this.cd2 = paramString;
    }

    public SkillTargetChooseType getTargetType() {
        return this.targetType;
    }

    void setTargetType(SkillTargetChooseType paramSkillTargetChooseType) {
        this.targetType = paramSkillTargetChooseType;
    }

    public int getDamageType() {
        return this.damageType;
    }

    void setDamageType(int paramInt) {
        this.damageType = paramInt;
    }

    public int getRang() {
        return this.rang;
    }

    void setRang(int paramInt) {
        this.rang = paramInt;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int paramInt) {
        this.length = paramInt;
    }

    public boolean isRang() {
        return this.isRang;
    }

    void setIsRang(boolean paramBoolean) {
        this.isRang = paramBoolean;
    }

    public int getMaxTarget() {
        return this.maxTarget;
    }

    void setMaxTarget(int paramInt) {
        this.maxTarget = paramInt;
    }

    public int getMaxHurt() {
        return this.maxHurt;
    }

    void setMaxHurt(int paramInt) {
        this.maxHurt = paramInt;
    }

    public boolean getVoltCalculate() {
        return this.voltCalculate;
    }

    void setVoltCalculate(boolean paramBoolean) {
        this.voltCalculate = paramBoolean;
    }

    public float getBaseTohit() {
        return this.baseTohit;
    }

    void setBaseTohit(float paramFloat) {
        this.baseTohit = paramFloat;
    }

    public boolean getCriticalCalculate() {
        return this.criticalCalculate;
    }

    void setCriticalCalculate(boolean paramBoolean) {
        this.criticalCalculate = paramBoolean;
    }

    public int getBaseCritical() {
        return this.baseCritical;
    }

    void setBaseCritical(int paramInt) {
        this.baseCritical = paramInt;
    }

    public int getBaseDodgeDefance() {
        return this.baseDodgeDefance;
    }

    void setBaseDodgeDefance(int paramInt) {
        this.baseDodgeDefance = paramInt;
    }

    public String getDamageFormuleType() {
        return this.damageFormuleType;
    }

    void setDamageFormuleType(String paramString) {
        this.damageFormuleType = paramString;
    }

    public float getBaodi() {
        return this.baodi;
    }

    void setBaodi(float paramFloat) {
        this.baodi = paramFloat;
    }

    public float getFormulea() {
        return this.formulea;
    }

    void setFormulea(float paramFloat) {
        this.formulea = paramFloat;
    }

    public float getFormuleb() {
        return this.formuleb;
    }

    void setFormuleb(float paramFloat) {
        this.formuleb = paramFloat;
    }

    public float getFormulec() {
        return this.formulec;
    }

    void setFormulec(float paramFloat) {
        this.formulec = paramFloat;
    }

    public float getFormuled() {
        return this.formuled;
    }

    void setFormuled(float paramFloat) {
        this.formuled = paramFloat;
    }

    public float getFormulee() {
        return this.formulee;
    }

    void setFormulee(float paramFloat) {
        this.formulee = paramFloat;
    }

    public float getFormulef() {
        return this.formulef;
    }

    void setFormulef(float paramFloat) {
        this.formulef = paramFloat;
    }

    public float getFormuleg() {
        return this.formuleg;
    }

    void setFormuleg(float paramFloat) {
        this.formuleg = paramFloat;
    }

    public float getFormuleh() {
        return this.formuleh;
    }

    void setFormuleh(float paramFloat) {
        this.formuleh = paramFloat;
    }

    public int getStartAngle() {
        return this.startAngle;
    }

    void setStartAngle(int paramInt) {
        this.startAngle = paramInt;
    }

    public int getEndAngle() {
        return this.endAngle;
    }

    void setEndAngle(int paramInt) {
        this.endAngle = paramInt;
    }

    public List<SkillEffectConfig> getEffectConfigs() {
        return this.effectConfigs;
    }

    void setEffectConfigs(List<SkillEffectConfig> paramList) {
        this.effectConfigs = paramList;
    }

    public IEntity copy() {
        SkillConfig localSkillConfig = new SkillConfig();
        localSkillConfig.setName(getName());
        localSkillConfig.setSkillCategory(getSkillCategory());
        localSkillConfig.setSkillId(getSkillId());
        localSkillConfig.setSkillLevel(getSkillLevel());
        localSkillConfig.setActiveOrPassive(getActiveOrPassive());
        localSkillConfig.setConsumeType(getConsumeType());
        localSkillConfig.setConsume1(getConsume1());
        localSkillConfig.setConsume2(getConsume2());
        localSkillConfig.setCd1(getCd1());
        localSkillConfig.setCd2(getCd2());
        localSkillConfig.setDamageType(getDamageType());
        localSkillConfig.setRang(getRang());
        localSkillConfig.setIsRang(isRang());
        localSkillConfig.setMaxTarget(getMaxTarget());
        localSkillConfig.setMaxHurt(getMaxHurt());
        localSkillConfig.setVoltCalculate(getVoltCalculate());
        localSkillConfig.setBaseTohit(getBaseTohit());
        localSkillConfig.setCriticalCalculate(getCriticalCalculate());
        localSkillConfig.setBaseCritical(getBaseCritical());
        localSkillConfig.setBaseDodgeDefance(getBaseDodgeDefance());
        localSkillConfig.setDamageFormuleType(getDamageFormuleType());
        localSkillConfig.setBaodi(getBaodi());
        localSkillConfig.setFormulea(getFormulea());
        localSkillConfig.setFormuleb(getFormuleb());
        localSkillConfig.setFormulec(getFormulec());
        localSkillConfig.setFormuled(getFormuled());
        localSkillConfig.setFormulee(getFormulee());
        localSkillConfig.setFormulef(getFormulef());
        localSkillConfig.setEffectConfigs(getEffectConfigs());
        return localSkillConfig;
    }

    public String getPirmaryKeyName() {
        return "skillId";
    }

    public String getPrimaryKeyValue() {
        return this.primary;
    }

    public List<Integer> getBuffIds() {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(Integer.valueOf(1));
        return localArrayList;
    }

    public float getResistSkillRate() {
        return this.resistSkillRate;
    }

    void setResistSkillRate(float paramFloat) {
        this.resistSkillRate = paramFloat;
    }

    public float getResistBuffRate() {
        return this.resistBuffRate;
    }

    void setResistBuffRate(float paramFloat) {
        this.resistBuffRate = paramFloat;
    }

    public Integer getCasttime() {
        return this.casttime;
    }

    void setCasttime(Integer paramInteger) {
        this.casttime = paramInteger;
    }

    public int getDamageDelay() {
        return this.damageDelay.intValue();
    }

    void setDamageDelay(Integer paramInteger) {
        this.damageDelay = paramInteger;
    }

    public boolean isRebound() {
        return this.rebound;
    }

    void setRebound(boolean paramBoolean) {
        this.rebound = paramBoolean;
    }

    public Integer getAddVigour() {
        return this.addVigour;
    }

    void setAddVigour(Integer paramInteger) {
        this.addVigour = paramInteger;
    }

    void setHatredN1(Float paramFloat) {
        this.hatredN1 = paramFloat.floatValue();
    }

    public float getHatredN1() {
        return this.hatredN1;
    }

    public float getHatredN2() {
        return this.hatredN2;
    }

    void setHatredN2(float paramFloat) {
        this.hatredN2 = paramFloat;
    }

    void setHatredType(Integer paramInteger) {
        this.hatredType = paramInteger;
    }

    public Integer getHatredType() {
        return this.hatredType;
    }

    public Map<String, Float> getAttributes() {
        return this.attributes;
    }

    void setAttributes(Map<String, Float> paramMap) {
        this.attributes = paramMap;
    }

    void setSkillType(SkillType paramSkillType) {
        this.skillType = paramSkillType;
    }

    public SkillType getSkillType() {
        return this.skillType;
    }

    public String[] getLianSkills() {
        return this.lianSkills;
    }

    void setLianSkills(String[] paramArrayOfString) {
        this.lianSkills = paramArrayOfString;
    }

    public float getSkillTriggerOdds() {
        return this.skillTriggerOdds;
    }

    void setSkillTriggerOdds(float paramFloat) {
        this.skillTriggerOdds = paramFloat;
    }

    public String getSkillTriggerFurmula() {
        return this.skillTriggerFurmula;
    }

    void setSkillTriggerFurmula(String paramString) {
        this.skillTriggerFurmula = paramString;
    }

    public float getZuoqiSkillBookOdds() {
        return this.zuoqiSkillBookOdds;
    }

    void setZuoqiSkillBookOdds(float paramFloat) {
        this.zuoqiSkillBookOdds = paramFloat;
    }

    public SkillFirePathType getFirePathType() {
        return this.firePathType;
    }

    void setFirePathType(SkillFirePathType paramSkillFirePathType) {
        this.firePathType = paramSkillFirePathType;
    }

    public boolean isLastingSkill() {
        return this.isLastingSkill;
    }

    void setLastingSkill(boolean paramBoolean) {
        this.isLastingSkill = paramBoolean;
    }

    public int getWaveNum() {
        return this.waveNum;
    }

    void setWaveNum(int paramInt) {
        this.waveNum = paramInt;
    }

    public boolean isCallSkill() {
        return this.callSkill;
    }

    void setCallSkill(boolean paramBoolean) {
        this.callSkill = paramBoolean;
    }

    public String[] getCallMonsters() {
        return this.callMonsters;
    }

    void setCallMonsters(String[] paramArrayOfString) {
        this.callMonsters = paramArrayOfString;
    }

    public float getCallAttributeOdds() {
        return this.callAttributeOdds;
    }

    void setCallAttributeOdds(float paramFloat) {
        this.callAttributeOdds = paramFloat;
    }

    public int getCallLifeTime() {
        return this.callLifeTime;
    }

    void setCallLifeTime(int paramInt) {
        this.callLifeTime = paramInt;
    }

    public boolean ispetskill() {
        return this.ispetskill;
    }

    public void setIspetskill(boolean paramBoolean) {
        this.ispetskill = paramBoolean;
    }
}

/*
 * Location: D:\jd-gui\dntg_start.jar
 * 
 * Qualified Name: com.xianling.bus.skill.configure.export.impl.SkillConfig
 * 
 * JD-Core Version: 0.7.0.1
 */