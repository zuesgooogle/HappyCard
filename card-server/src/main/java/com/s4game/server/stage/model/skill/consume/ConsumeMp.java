package com.s4game.server.stage.model.skill.consume;

import org.springframework.stereotype.Component;

import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.element.ISkill;
import com.s4game.server.stage.model.core.fight.ISkillConsume;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月4日 下午5:15:49
 *
 */
@Component
public class ConsumeMp implements ISkillConsume {

    @Override
    public Object execute(IFighter fighter, ISkill skill) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEnough(IFighter fighter, ISkill skill) {
        // TODO Auto-generated method stub
        return false;
    }

}
