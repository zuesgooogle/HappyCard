package com.s4game.server.stage.model.core.element;

public enum BattleMode {
    
    /**
     * 和平攻击模式
     */
    PEACE(0), 
    
    /**
     * 组队
     */
    TEAM(1), 
    
    /**
     * 工会
     */
    GUILD(2), 
    
    /**
     * 全体
     */
    ALL(Integer.valueOf(3)), 

    /**
     * 善恶
     */
    SHANE(Integer.valueOf(4)), 
    
    MONSTER_NORMAL(Integer.valueOf(5));

    private final Integer val;

    private BattleMode(Integer paramInteger) {
        this.val = paramInteger;
    }

    public Integer getVal() {
        return this.val;
    }

    public static BattleMode convert(int paramInt) {
        switch (paramInt) {
        case 0:
            return PEACE;
        case 1:
            return TEAM;
        case 2:
            return GUILD;
        case 3:
            return ALL;
        case 4:
            return SHANE;
        }
        return PEACE;
    }
}

/*
 * Location: D:\jd-gui\dntg_start.jar
 * 
 * Qualified Name: com.xianling.stage.model.core.element.BattleMode
 * 
 * JD-Core Version: 0.7.0.1
 */