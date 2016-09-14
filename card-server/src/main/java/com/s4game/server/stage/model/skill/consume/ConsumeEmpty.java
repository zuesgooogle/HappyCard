package com.s4game.server.stage.model.skill.consume;

import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.element.ISkill;
import com.s4game.server.stage.model.core.fight.ISkillConsume;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月4日 下午5:27:26
 *
 */

public class ConsumeEmpty implements ISkillConsume {

    @Override
    public Object execute(IFighter fighter, ISkill skill) {
        return null;
    }

    @Override
    public boolean isEnough(IFighter fighter, ISkill skill) {
        return true;
    }

}
