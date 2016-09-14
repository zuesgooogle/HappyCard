package com.s4game.server.stage.model.core.fight;

import com.s4game.server.gamerule.skill.SkillTargetChooseType;
import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.element.ISkill;

public interface ISkillTarget {
    
    public boolean inRange(ISkill skill, IFighter fighter1, IFighter fighter2);

    public SkillTargetChooseType getTargetChooseType();
}
