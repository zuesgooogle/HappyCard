package com.s4game.server.stage.model.fight;

import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.element.ISkill;
import com.s4game.server.stage.model.core.element.impl.state.StateEventType;
import com.s4game.server.stage.model.core.stage.IStage;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月4日 下午4:14:54
 *
 */

public class SkillProcessHelper {

    public static boolean skillCheck(ISkill skill, IFighter fighter) {
        //TODO skill consume
        return true;
    }
    
    
    public static boolean stageCheck(IStage stage) {
        return true;
    }
    
    public static boolean fighterCheck(IFighter fighter) {
        return (!fighter.getStateManager().isDead() && !fighter.getStateManager().isForbidden(StateEventType.ATTACK));
    }
    
    public static boolean skillReadyFireCheck(IStage stage, IFighter fighter, ISkill skill) {
        if( null == skill ) {
            return false;
        }
        
        return stageCheck(stage) && skillCheck(skill, fighter) && fighterCheck(fighter);
     }
}
