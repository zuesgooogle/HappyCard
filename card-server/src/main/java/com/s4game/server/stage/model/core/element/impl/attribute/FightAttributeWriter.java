package com.s4game.server.stage.model.core.element.impl.attribute;


public final class FightAttributeWriter {

    private static FightAttributeWriter writer = new FightAttributeWriter();

    protected static FightAttributeWriter get() {
        return writer;
    }

    public void setCurHp(AbsFightAttribute fightAttribute, float value) {
        fightAttribute.setCurHp(value);
    }

    public void setCurMp(AbsFightAttribute fightAttribute, float value) {
        fightAttribute.setCurMp(value);
    }

    public void setMaxHp(AbsFightAttribute fightAttribute, float value) {
        fightAttribute.setMaxHp(value);
    }

    public void setMaxMp(AbsFightAttribute fightAttribute, float value) {
        fightAttribute.setMaxMp(value);
    }

    public void setWuLiGongJi(AbsFightAttribute fightAttribute, float value) {
        fightAttribute.setWuLiGongJi(value);
    }

    public void setWuLiFangYu(AbsFightAttribute fightAttribute, float value) {
        fightAttribute.setWuLiFangYu(value);
    }

    public void setYiDongSuDu(AbsFightAttribute fightAttribute, float value) {
        fightAttribute.setYiDongSuDu(value);
    }

    public void setGongJiSuDu(AbsFightAttribute fightAttribute, float value) {
        fightAttribute.setGongJiSuDu(value);
    }
}
