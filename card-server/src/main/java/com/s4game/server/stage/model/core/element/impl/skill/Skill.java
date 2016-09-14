package com.s4game.server.stage.model.core.element.impl.skill;

import com.s4game.server.bus.skill.configure.export.impl.SkillConfig;
import com.s4game.server.bus.skill.configure.export.impl.SkillLianConfig;
import com.s4game.server.gamerule.skill.SkillTargetChooseType;
import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.element.ISkill;
import com.s4game.server.stage.model.core.fight.ISkillHatred;
import com.s4game.server.stage.model.core.fight.ISkillTarget;

public class Skill implements ISkill {
    
    private String cdType1;
    private String cdType2;
    
    private ISkillHatred skillHatred;
    
    private SkillConfig skillConfig;
    private SkillLianConfig[] lianSkillConfigs;
    
    private int index = 0;
    private ISkillTarget[] skillTargets;
    private PublicCdManager publicCdManager;

    public Skill(SkillConfig skillConfig) {
        this.skillConfig = skillConfig;
        this.cdType1 = this.skillConfig.getCd1();
        this.cdType2 = this.skillConfig.getCd2();
    }

    public PublicCdManager getPublicCdManager() {
        return this.publicCdManager;
    }

    @Override
    public void setPublicCdManager(PublicCdManager publicCdManager) {
        this.publicCdManager = publicCdManager;
    }

    @Override
    public long remainCd() {
        long l1 = this.publicCdManager.remainCd(this.cdType1);
        long l2 = this.publicCdManager.remainCd(this.cdType2);
        return l1 > l2 ? l1 : l2;
    }

    @Override
    public void toCd(IFighter fighter) {
        if (null != this.cdType1) {
            this.publicCdManager.toDynamicCd(this.cdType1, fighter);
        }
        
        if (null != this.cdType2) {
            this.publicCdManager.toCd(this.cdType2);
        }
    }

    @Override
    public String getCategory() {
        return this.skillConfig.getSkillCategory();
    }

    @Override
    public String getId() {
        return this.skillConfig.getSkillId();
    }

    @Override
    public Object getHarmEffect() {
        return this.skillConfig.getHarmEffect();
    }

    @Override
    public Integer getDynamicCd() {
        return this.publicCdManager.getCd(this.cdType1);
    }

    @Override
    public ISkillHatred getSkillHatred() {
        return this.skillHatred;
    }

    public void setSkillHatred(ISkillHatred skillHatred) {
        this.skillHatred = skillHatred;
    }

    public SkillTargetChooseType getTargetType() {
        return this.skillConfig.getTargetType();
    }

    public SkillConfig getSkillConfig() {
        return this.skillConfig;
    }

    public void setLianSkillConfigs(SkillLianConfig[] skillLianConfig) {
        this.lianSkillConfigs = skillLianConfig;
    }

    public SkillLianConfig loadLianZhaoConfigByIndex() {
        if (this.lianSkillConfigs == null) {
            return null;
        }
        if (this.lianSkillConfigs.length < this.index + 1) {
            return null;
        }
        return this.lianSkillConfigs[this.index];
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        if (this.lianSkillConfigs == null) {
            this.index = 0;
        } else {
            if (index >= this.lianSkillConfigs.length) {
                index = this.lianSkillConfigs.length - 1;
            }
            this.index = index;
        }
    }

    public boolean hasLianZhao() {
        return (this.lianSkillConfigs != null) && (this.lianSkillConfigs.length > 0);
    }

    public ISkillTarget getTarget() {
        return this.skillTargets[this.index];
    }

    public void setSkillTargets(ISkillTarget[] skillTarget) {
        this.skillTargets = skillTarget;
    }
}