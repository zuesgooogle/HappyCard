package com.s4game.server.gamerule.skill;

public enum SkillTargetChooseType {
    
    ENEMY(1), 
    
    SELF(2);

    private int val;

    public static boolean isSelf(SkillTargetChooseType skillTargetChooseType) {
        return SELF.equals(skillTargetChooseType);
    }

    public static boolean isEnemy(SkillTargetChooseType skillTargetChooseType) {
        return ENEMY.equals(skillTargetChooseType);
    }

    public static boolean isSingleTarget(SkillTargetChooseType skillTargetChooseType) {
        return ENEMY.equals(skillTargetChooseType);
    }

    public static boolean isAOE(SkillTargetChooseType skillTargetChooseType) {
        return false;
    }

    private SkillTargetChooseType(int paramInt) {
        this.val = paramInt;
    }

    public int getVal() {
        return this.val;
    }
}