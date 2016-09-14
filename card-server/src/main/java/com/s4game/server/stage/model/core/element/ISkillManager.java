package com.s4game.server.stage.model.core.element;

import java.util.Collection;
import java.util.List;

public interface ISkillManager {
    
    public void addSkill(ISkill skill);

    public void removeSkill(String skillCategory);

    public ISkill getSkillByCategory(String skillCategory);

    public ISkill getSkillById(String paramString);

    public boolean isReadyForFire(ISkill skill);

    public void setReadyForFire(ISkill skill);

    public boolean skillWaveCheck(ISkill skill);

    public long getRemainCd();

    public Collection<ISkill> getSkillList();

    public ISkill getNextUseableSkill();

    public ISkill getNextFireSkill();

    public void setNextFireSkill(ISkill skill);

    public List<String> getSkillIds();

    public List<String> getSkillMonsterIds();

    public void addSkillMonsterIds(String paramString);

    public void removeSkillCallMonster(String paramString);

    public Object[] getConfigSkillIds();
}