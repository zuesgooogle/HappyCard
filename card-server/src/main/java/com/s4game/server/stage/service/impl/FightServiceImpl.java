package com.s4game.server.stage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.element.ISkill;
import com.s4game.server.stage.model.core.element.ISkillManager;
import com.s4game.server.stage.model.core.fight.ISkillConsume;
import com.s4game.server.stage.model.core.stage.ElementType;
import com.s4game.server.stage.model.core.stage.IStage;
import com.s4game.server.stage.model.fight.SkillProcessHelper;
import com.s4game.server.stage.model.skill.consume.SkillConsumeFactory;
import com.s4game.server.stage.service.IFightService;
import com.s4game.server.stage.service.IStageService;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月4日 下午3:22:29
 *
 */
@Component
public class FightServiceImpl implements IFightService {

    @Resource
    private IStageService stageService;
    
    @Override
    public void skillReadyFire(String stageId, String roleId, String attacker, String skillCategory) {
        IStage stage = stageService.getStage(stageId);
        IFighter fighter = stage.getElement(attacker, ElementType.FIGHTER);
        if( null == fighter ) {
            return;
        }
        
        //技能施法者 == 自己
        boolean self = attacker.equals(roleId);
        ISkillManager skillManager = fighter.getSkillManager();
        ISkill skill = skillManager.getSkillByCategory(skillCategory);
        
        Object[] checkResult = skillReadyFireCheck(stage, fighter, skill);
        if( checkResult != null ) {
            if( self ) {
                //TODO send message 42001
            }
            return;
        }
        
        skill.toCd(fighter);
        skillManager.setReadyForFire(skill);
        
        //skill consume execute
        ISkillConsume skillConsume = SkillConsumeFactory.getSkillConsume(skill.getSkillConfig().getConsumeType());
        skillConsume.execute(fighter, skill);
        
        //给技能目标返回消息 42001
        
        
    }

    private Object[] skillReadyFireCheck(IStage stage, IFighter fighter, ISkill skill) {
        if( null == skill ) {
            return new Object[] {0};
        }
        
        long remainCd = skill.remainCd();
        if( remainCd > 0 ) {
            return new Object[]{-1, skill.getCategory(), remainCd};
        }
        
        if( !SkillProcessHelper.skillReadyFireCheck(stage, fighter, skill) ) {
            return new Object[] {0};
        }
        return null;
    }
    
    @Override
    public void skillFire(String stageId, String roleId, String attacker, String skillCategory, String[] targets) {
        IStage stage = stageService.getStage(stageId);
        IFighter fighter = stage.getElement(attacker, ElementType.FIGHTER);
        if( null == fighter ) {
            return;
        }
        
        ISkill skill = fighter.getSkillManager().getSkillByCategory(skillCategory);
        if( !SkillProcessHelper.skillReadyFireCheck(stage, fighter, skill) ) {
            return ;
        }
        
        //confirm target
        
    }

}
