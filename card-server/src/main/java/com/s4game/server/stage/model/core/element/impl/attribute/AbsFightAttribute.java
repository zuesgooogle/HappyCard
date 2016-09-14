package com.s4game.server.stage.model.core.element.impl.attribute;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import com.s4game.server.stage.model.core.element.IAttributeFormula;
import com.s4game.server.stage.model.core.element.IFightAttribute;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月24日 上午11:49:14
 * 
 */

public abstract class AbsFightAttribute implements IFightAttribute {

    private AttributeFormulaSearcher attributeFormulaSearcher;
    private FightAttributeStatistic attributeStatistic;

    protected float curHp = 0;
    protected float curMp = 0;
    protected float maxHp = 0;
    protected float maxMp = 0;

    private float wuLiGongJi = 0;
    private float wuLiFangYu = 0;
    private float yiDongSuDu = 0;
    private float gongJiSuDu = 0;

    private ConcurrentHashMap<String, IAttributeFormula> referenceFormulas = new ConcurrentHashMap();

    public AbsFightAttribute(AttributeFormulaSearcher attributeFormulaSearcher) {
        this.attributeFormulaSearcher = attributeFormulaSearcher;
        this.attributeStatistic = new FightAttributeStatistic();
    }

    protected void addAttributeFormula(String attr) {
        IAttributeFormula attributeFormula = this.attributeFormulaSearcher.findReference(attr);
        if (null != attributeFormula) {
            this.referenceFormulas.put(attributeFormula.getClass().getSimpleName(), attributeFormula);
        }
    }

    protected void refresh() {
        if (this.referenceFormulas.size() == 0) {
            return;
        }
        Iterator<IAttributeFormula> iterator = this.referenceFormulas.values().iterator();
        while (iterator.hasNext()) {
            IAttributeFormula attributeFormula = iterator.next();
            attributeFormula.refreshAttribute(this, FightAttributeWriter.get());
        }
        this.referenceFormulas.clear();
    }

    @Override
    public float getGongJiSuDu() {
        return this.gongJiSuDu;
    }

    @Override
    public float getYiDongSuDu() {
        return this.yiDongSuDu;
    }

    @Override
    public float getWuLiGongJi() {
        return this.wuLiGongJi;
    }

    @Override
    public float getWuLiFangYu() {
        return this.wuLiFangYu;
    }

    @Override
    public float getBaoJi() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getShanBi() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getCurHp() {
        return this.curHp;
    }

    @Override
    public float getMaxHp() {
        return this.maxHp;
    }

    @Override
    public float getCurMp() {
        return this.curMp;
    }

    @Override
    public float getMaxMp() {
        return this.maxMp;
    }

    @Override
    public float getFanTanJiLv() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getFanTanHarmRate() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getGeDang() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getPoJi() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getGeDangRate() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getMabi() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getMabiResist() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getYunxuan() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getYunxuanResist() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getBinghuan() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getBinghuanResist() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getXixie() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getXixieResist() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getShishen() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getCriticalb() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getCriticalc() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getPojia() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getQiege() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getJiangu() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getShenyou() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getDamage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getDefence() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getPerdamage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getRedamage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getReph() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getLeidamage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getLeidefence() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getMudamage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getMudefence() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getShuidamage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getShuidefence() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getHuodamage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getHuodefence() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getTudamage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getTudefence() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void resetHpMp() {
        setCurHp(getMaxHp());
        setCurMp(getMaxMp());
    }

    @Override
    public void setCurMp(float mp) {
        if (mp > getMaxMp()) {
            mp = getMaxMp();
        }

        if (mp < 0) {
            mp = 0;
        }
        this.curHp = mp;
        this.attributeStatistic.changeHpOrMap();
    }

    @Override
    public void setCurHp(float hp) {
        if (hp > getMaxHp()) {
            hp = getMaxHp();
        }

        if (hp < 0) {
            hp = 0;
        }
        this.curHp = hp;
        this.attributeStatistic.changeHpOrMap();
    }

    @Override
    public void setShanbiVal(int paramInt) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getShanbiVal() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMaxShanbiVal() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void setMaxHp(float maxHp) {
        this.maxHp = maxHp;
    }

    public void setMaxMp(float maxMp) {
        this.maxMp = maxMp;
    }

    public void setWuLiGongJi(float wuLiGongJi) {
        this.wuLiGongJi = wuLiGongJi;
    }

    public void setWuLiFangYu(float wuLiFangYu) {
        this.wuLiFangYu = wuLiFangYu;
    }

    public void setYiDongSuDu(float yiDongSuDu) {
        this.yiDongSuDu = yiDongSuDu;
    }

    public void setGongJiSuDu(float gongJiSuDu) {
        this.gongJiSuDu = gongJiSuDu;
    }

    @Override
    public FightAttributeStatistic getAttributeStatistic() {
        return attributeStatistic;
    }

}
