package com.s4game.server.stage.configure.export.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.s4game.core.data.IEntity;
import com.s4game.server.bus.xunbao.util.RandomUtil;
import com.s4game.server.gamerule.zubao.entity.DropConfig;
import com.s4game.server.stage.configure.constants.MonsterDifficulty;

public class MonsterConfig implements IEntity {
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    private int monsterType;
    private String name;
    private String face;
    private int faceto;
    private int storyLevel;
    private int level;
    private int category;
    private MonsterDifficulty difficulty;
    private int townflag;
    private Map<String, Float> floatMap = new HashMap();
    private List<String> skillList;
    private String camp;
    private int ifactive;
    private int eyeshot;
    private boolean chasestanding;
    private int chainradius;
    private List<String> chainTarget;
    private int basicExp;
    private int basicMoney;
    private List<DropConfig> dropsConfig = new ArrayList();
    private int noBaoHu;
    private List<MonsterDropGoodsConfig> taskGoodsList;
    private BonusDrop bonusDrop;
    private float massparameter;
    private int specialType;
    private int drExp;
    private int drMoney;
    private int prExp;
    private int prMoney;
    private int failBuff;
    private int succeedBuff;
    private int levelPunish;
    private int noExpAdd;
    private int lasttime;
    private List<DropRule> dropRules;
    private DropRule bianShiDropRule;
    private Map<Integer, String> faBaoIds;
    private List<MoneyDropRule> moneyDropRules;
    private Integer dropGoodsDuration;
    private Integer bianShiDuration;
    private Integer disappearDuration;
    private Map<String, Object> fujiaMap;
    private int maxrange;
    private boolean guDingHarm;
    private int guDingHarmVal = 0;

    private String advancedAiId;
    private boolean dailyDropLimit;
    private int zhanli;
    private float recoverHp;
    private boolean belongHurtes;
    private boolean needFullHp;

    public int getMaxrange() {
        return this.maxrange;
    }

    void setMaxrange(int paramInt) {
        this.maxrange = paramInt;
    }

    public List<DropRule> getDropRules() {
        return this.dropRules;
    }

    void setDropRules(List<DropRule> paramList) {
        this.dropRules = paramList;
    }

    public BonusDrop getBonusDrop() {
        return this.bonusDrop;
    }

    void setBonusDrop(BonusDrop paramBonusDrop) {
        this.bonusDrop = paramBonusDrop;
    }

    public BonusDrop newBonusDrop(float paramFloat1, float paramFloat2) {
        return new BonusDrop(paramFloat1, paramFloat2);
    }

    public IEntity copy() {
        MonsterConfig localMonsterConfig = new MonsterConfig();
        localMonsterConfig.setId(getId());
        localMonsterConfig.setMonsterType(getMonsterType());
        localMonsterConfig.setName(getName());
        localMonsterConfig.setFace(getFace());
        localMonsterConfig.setFaceto(getFaceto());
        localMonsterConfig.setStoryLevel(getStoryLevel());
        localMonsterConfig.setLevel(getLevel());
        localMonsterConfig.setCategory(getCategory());
        localMonsterConfig.setDifficulty(getDifficulty());
        localMonsterConfig.setTownflag(getTownflag());
        localMonsterConfig.setFloatMap(getFloatMap());
        localMonsterConfig.setSkillList(getSkillList());
        localMonsterConfig.setCamp(getCamp());
        localMonsterConfig.setIfactive(getIfactive());
        localMonsterConfig.setEyeshot(getEyeshot());
        localMonsterConfig.setChasestanding(isChasestanding());
        localMonsterConfig.setChainradius(getChainradius());
        localMonsterConfig.setChainTarget(getChainTarget());
        localMonsterConfig.setBasicExp(getBasicExp());
        localMonsterConfig.setBasicMoney(getBasicMoney());
        localMonsterConfig.setDropsConfig(getDropsConfig());
        localMonsterConfig.setNoBaoHu(getNoBaoHu());
        localMonsterConfig.setTaskGoodsList(getTaskGoodsList());
        localMonsterConfig.setBonusDrop(getBonusDrop());
        localMonsterConfig.setMassparameter(getMassparameter());
        localMonsterConfig.setSpecialType(getSpecialType());
        localMonsterConfig.setDrExp(getDrExp());
        localMonsterConfig.setDrMoney(getDrMoney());
        localMonsterConfig.setPrExp(getPrExp());
        localMonsterConfig.setPrMoney(getPrMoney());
        localMonsterConfig.setFailBuff(getFailBuff());
        localMonsterConfig.setSucceedBuff(getSucceedBuff());
        localMonsterConfig.setLevelPunish(getLevelPunish());
        localMonsterConfig.setNoExpAdd(getNoExpAdd());
        localMonsterConfig.setLasttime(getLasttime());
        return localMonsterConfig;
    }

    public String getPirmaryKeyName() {
        return "id";
    }

    public String getPrimaryKeyValue() {
        return this.id.trim();
    }

    public String getId() {
        return this.id;
    }

    void setId(String paramString) {
        this.id = paramString;
    }

    public int getMonsterType() {
        return this.monsterType;
    }

    void setMonsterType(int paramInt) {
        this.monsterType = paramInt;
    }

    public String getName() {
        return this.name;
    }

    void setName(String paramString) {
        this.name = paramString;
    }

    public String getFace() {
        return this.face;
    }

    void setFace(String paramString) {
        this.face = paramString;
    }

    public int getFaceto() {
        return this.faceto;
    }

    void setFaceto(int paramInt) {
        this.faceto = paramInt;
    }

    public int getStoryLevel() {
        return this.storyLevel;
    }

    void setStoryLevel(int paramInt) {
        this.storyLevel = paramInt;
    }

    public int getLevel() {
        return this.level;
    }

    void setLevel(int paramInt) {
        this.level = paramInt;
    }

    public int getCategory() {
        return this.category;
    }

    void setCategory(int paramInt) {
        this.category = paramInt;
    }

    public MonsterDifficulty getDifficulty() {
        return this.difficulty;
    }

    void setDifficulty(MonsterDifficulty paramMonsterDifficulty) {
        this.difficulty = paramMonsterDifficulty;
    }

    public int getTownflag() {
        return this.townflag;
    }

    void setTownflag(int paramInt) {
        this.townflag = paramInt;
    }

    public Map<String, Float> getFloatMap() {
        return this.floatMap;
    }

    void setFloatMap(Map<String, Float> paramMap) {
        this.floatMap = paramMap;
    }

    public List<String> getSkillList() {
        return this.skillList;
    }

    void setSkillList(List<String> paramList) {
        this.skillList = paramList;
    }

    public String getCamp() {
        return this.camp;
    }

    void setCamp(String paramString) {
        this.camp = paramString;
    }

    public int getIfactive() {
        return this.ifactive;
    }

    void setIfactive(int paramInt) {
        this.ifactive = paramInt;
    }

    public int getEyeshot() {
        return this.eyeshot;
    }

    void setEyeshot(int paramInt) {
        this.eyeshot = paramInt;
    }

    public boolean isChasestanding() {
        return this.chasestanding;
    }

    void setChasestanding(boolean paramBoolean) {
        this.chasestanding = paramBoolean;
    }

    public int getChainradius() {
        return this.chainradius;
    }

    void setChainradius(int paramInt) {
        this.chainradius = paramInt;
    }

    public List<String> getChainTarget() {
        return this.chainTarget;
    }

    void setChainTarget(List<String> paramList) {
        this.chainTarget = paramList;
    }

    public int getBasicExp() {
        return this.basicExp;
    }

    void setBasicExp(int paramInt) {
        this.basicExp = paramInt;
    }

    public int getBasicMoney() {
        return this.basicMoney;
    }

    void setBasicMoney(int paramInt) {
        this.basicMoney = paramInt;
    }

    public List<DropConfig> getDropsConfig() {
        return this.dropsConfig;
    }

    void setDropsConfig(List<DropConfig> paramList) {
        this.dropsConfig = paramList;
    }

    public int getNoBaoHu() {
        return this.noBaoHu;
    }

    void setNoBaoHu(int paramInt) {
        this.noBaoHu = paramInt;
    }

    public List<MonsterDropGoodsConfig> getTaskGoodsList() {
        return this.taskGoodsList;
    }

    void setTaskGoodsList(List<MonsterDropGoodsConfig> paramList) {
        this.taskGoodsList = paramList;
    }

    public float getMassparameter() {
        return this.massparameter;
    }

    void setMassparameter(float paramFloat) {
        this.massparameter = paramFloat;
    }

    public int getSpecialType() {
        return this.specialType;
    }

    void setSpecialType(int paramInt) {
        this.specialType = paramInt;
    }

    public int getDrExp() {
        return this.drExp;
    }

    void setDrExp(int paramInt) {
        this.drExp = paramInt;
    }

    public int getDrMoney() {
        return this.drMoney;
    }

    void setDrMoney(int paramInt) {
        this.drMoney = paramInt;
    }

    public int getPrExp() {
        return this.prExp;
    }

    void setPrExp(int paramInt) {
        this.prExp = paramInt;
    }

    public int getPrMoney() {
        return this.prMoney;
    }

    void setPrMoney(int paramInt) {
        this.prMoney = paramInt;
    }

    public int getFailBuff() {
        return this.failBuff;
    }

    void setFailBuff(int paramInt) {
        this.failBuff = paramInt;
    }

    public int getSucceedBuff() {
        return this.succeedBuff;
    }

    void setSucceedBuff(int paramInt) {
        this.succeedBuff = paramInt;
    }

    public int getLevelPunish() {
        return this.levelPunish;
    }

    void setLevelPunish(int paramInt) {
        this.levelPunish = paramInt;
    }

    public int getNoExpAdd() {
        return this.noExpAdd;
    }

    void setNoExpAdd(int paramInt) {
        this.noExpAdd = paramInt;
    }

    public int getLasttime() {
        return this.lasttime;
    }

    void setLasttime(int paramInt) {
        this.lasttime = paramInt;
    }

    public float getMonsterArrtibute(String paramString) {
        Float localFloat = (Float) this.floatMap.get(paramString);
        if (null == localFloat) {
            return 0.0F;
        }
        return localFloat.floatValue();
    }

    public Map<Integer, String> getFaBaoIds() {
        return this.faBaoIds;
    }

    void setFaBaoIds(Map<Integer, String> paramMap) {
        this.faBaoIds = paramMap;
    }

    public Map<String, Object> getFujiaMap() {
        return this.fujiaMap;
    }

    void setFujiaMap(Map<String, Object> paramMap) {
        this.fujiaMap = paramMap;
    }

    public DropRule getBianShiDropRule() {
        return this.bianShiDropRule;
    }

    void setBianShiDropRule(DropRule paramDropRule) {
        this.bianShiDropRule = paramDropRule;
    }

    public Integer getDropGoodsDuration() {
        return this.dropGoodsDuration;
    }

    public Integer getBianShiDuration() {
        return this.bianShiDuration;
    }

    public Integer getDisappearDuration() {
        return this.disappearDuration;
    }

    void setDropGoodsDuration(Integer paramInteger) {
        this.dropGoodsDuration = paramInteger;
    }

    void setBianShiDuration(Integer paramInteger) {
        this.bianShiDuration = paramInteger;
    }

    void setDisappearDuration(Integer paramInteger) {
        this.disappearDuration = paramInteger;
    }

    public List<MoneyDropRule> getMoneyDropRules() {
        return this.moneyDropRules;
    }

    void setMoneyDropRules(List<MoneyDropRule> paramList) {
        this.moneyDropRules = paramList;
    }

    public boolean isGuDingHarm() {
        return this.guDingHarm;
    }

    void setGuDingHarm(boolean paramBoolean) {
        this.guDingHarm = paramBoolean;
    }

    public int getGuDingHarmVal() {
        return this.guDingHarmVal;
    }

    void setGuDingHarmVal(int paramInt) {
        this.guDingHarmVal = paramInt;
    }

    public String getAdvancedAiId() {
        return this.advancedAiId;
    }

    void setAdvancedAiId(String paramString) {
        this.advancedAiId = paramString;
    }

    public boolean isDailyDropLimit() {
        return this.dailyDropLimit;
    }

    void setDailyDropLimit(boolean paramBoolean) {
        this.dailyDropLimit = paramBoolean;
    }

    void setFightRecord(Integer paramInteger) {
        this.zhanli = paramInteger.intValue();
    }

    public int getZhanli() {
        return this.zhanli;
    }

    public float getRecoverHp() {
        return this.recoverHp;
    }

    void setRecoverHp(float paramFloat) {
        this.recoverHp = paramFloat;
    }

    public boolean isBelongHurtes() {
        return this.belongHurtes;
    }

    void setBelongHurtes(boolean paramBoolean) {
        this.belongHurtes = paramBoolean;
    }

    public boolean isNeedFullHp() {
        return this.needFullHp;
    }

    void setNeedFullHp(boolean paramBoolean) {
        this.needFullHp = paramBoolean;
    }

    public class BonusDrop {
        private float odds;
        private float rate;

        public BonusDrop(float paramFloat1, float paramFloat2) {
            this.odds = paramFloat1;
            this.rate = paramFloat2;
        }

        public float getOdds() {
            return this.odds;
        }

        void setOdds(float paramFloat) {
            this.odds = paramFloat;
        }

        public float getRate() {
            return this.rate;
        }

        void setRate(float paramFloat) {
            this.rate = paramFloat;
        }

        public boolean canDrop() {
            return RandomUtil.getFloatRandom() < this.odds;
        }
    }
}