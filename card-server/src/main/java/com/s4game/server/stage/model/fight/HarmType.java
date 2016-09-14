package com.s4game.server.stage.model.fight;

public enum HarmType {
    
    PUTONG(Integer.valueOf(0)), 
    
    SHANBI(Integer.valueOf(1)), 
    
    BAOJI(Integer.valueOf(2)), 
    
    GEDANG(Integer.valueOf(3)), 
    
    ZHILIAO(Integer.valueOf(4)), 
    
    ZHILIAOBAOJI(Integer.valueOf(5)), 
    
    BianShi(Integer.valueOf(6)), 
    
    FANSHAN(Integer.valueOf(7)), 
    
    POJI(Integer.valueOf(8)), 
    
    BIHU(Integer.valueOf(9)), 
    
    BAOJIBIHU(Integer.valueOf(10)), 
    
    DIDANG(Integer.valueOf(11));

    private final Integer type;

    private HarmType(Integer paramInteger) {
        this.type = paramInteger;
    }

    public Integer getVal() {
        return this.type;
    }

    public static boolean isZhiLiaoHarmType(HarmType paramHarmType) {
        return (paramHarmType.equals(ZHILIAO)) || (paramHarmType.equals(ZHILIAOBAOJI));
    }
}