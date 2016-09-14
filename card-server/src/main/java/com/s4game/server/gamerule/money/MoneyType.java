package com.s4game.server.gamerule.money;


/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月16日 下午6:44:26
 * 
 */

public enum MoneyType {

    YXB(1), 
    
    YB(2), 
    
    BIND_YB(3), 
    
    FROZEN_YB(4), 
    
    BANGGONG(5);

    private int intType;

    private MoneyType(int intType) {
        this.intType = intType;
    }

    public int getIntType() {
        return this.intType;
    }

    public static MoneyType getType(int paramInt) {
        switch (paramInt) {
        case 1:
            return YXB;
        case 2:
            return YB;
        case 3:
            return BIND_YB;
        case 4:
            return FROZEN_YB;
        case 5:
            return BANGGONG;
        }
        throw new UnknownError("unknown money intType! " + paramInt);
    }

    public static long accountTran(MoneyType paramMoneyType, long paramLong) {
        if (paramMoneyType.equals(YB)) {
            return paramLong * 5L;
        }
        if (paramMoneyType.equals(BIND_YB)) {
            return (int) Math.ceil(paramLong / 5L);
        }
        return -1L;
    }

}
