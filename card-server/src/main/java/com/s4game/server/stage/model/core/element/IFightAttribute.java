package com.s4game.server.stage.model.core.element;

import java.util.Map;

import com.s4game.server.stage.model.core.element.impl.attribute.FightAttributeStatistic;

public interface IFightAttribute extends IElementComponent {
    
    public void replaceBaseAttribute(BaseAttributeType baseAttributeType, Map<String, Float> map);

    public void incrBaseAttribute(BaseAttributeType baseAttributeType, Map<String, Float> map);

    public void descBaseAttribute(BaseAttributeType baseAttributeType, Map<String, Float> map);

    public void clearBaseAttribute(BaseAttributeType baseAttributeType);

    public float getNoEffectAttribute(String paramString);

    public float getEffectAttribute(String paramString);

    public float getGongJiSuDu();

    public float getYiDongSuDu();

    public float getWuLiGongJi();

    public float getWuLiFangYu();

    public float getMaxHp();

    public float getMaxMp();

    public float getBaoJi();

    public float getShanBi();

    public float getCurHp();

    public float getCurMp();

    public float getFanTanJiLv();

    public float getFanTanHarmRate();

    public float getGeDang();

    public float getPoJi();

    public float getGeDangRate();

    public float getMabi();

    public float getMabiResist();

    public float getYunxuan();

    public float getYunxuanResist();

    public float getBinghuan();

    public float getBinghuanResist();

    public float getXixie();

    public float getXixieResist();

    public float getShishen();

    public float getCriticalb();

    public float getCriticalc();

    public float getPojia();

    public float getQiege();

    public float getJiangu();

    public float getShenyou();

    public float getDamage();

    public float getDefence();

    public float getPerdamage();

    public float getRedamage();

    public float getReph();

    public float getLeidamage();

    public float getLeidefence();

    public float getMudamage();

    public float getMudefence();

    public float getShuidamage();

    public float getShuidefence();

    public float getHuodamage();

    public float getHuodefence();

    public float getTudamage();

    public float getTudefence();

    public void resetHpMp();

    public void setCurMp(float paramFloat);

    public void setCurHp(float paramFloat);

    public void setShanbiVal(int paramInt);

    public int getShanbiVal();

    public int getMaxShanbiVal();

    public FightAttributeStatistic getAttributeStatistic();
}