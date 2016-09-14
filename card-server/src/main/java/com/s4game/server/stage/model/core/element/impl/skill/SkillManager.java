package com.s4game.server.stage.model.core.element.impl.skill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.s4game.server.gamerule.skill.SkillType;
import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.element.ISkill;
import com.s4game.server.stage.model.core.element.ISkillManager;
import com.s4game.server.stage.model.fight.SkillProcessHelper;

@Component
public class SkillManager implements ISkillManager {
    
    protected PublicCdManager cdManager;
    
    private Map<String, ISkill> skillMap = new HashMap();
    private Map<String, ISkill> skillCategoryMap = new HashMap();
    private Map<String, SkillStatus> readyForFireSkillMap = new HashMap();
    private List<String> skillMonsterIds;
    private List<ISkill> skillList = new ArrayList();
    private int index = 0;
    private ISkill lastingSkill;
    private IFighter fighter;
    
    protected ISkill nextFireSkill;

    public SkillManager() {}

    public SkillManager(IFighter fighter) {
        this.cdManager = new PublicCdManager();
        this.fighter = fighter;
    }

    @Override
    public void addSkill(ISkill skill) {
        if (skill == null) {
            return;
        }
        skill.setPublicCdManager(this.cdManager);
        
        this.skillMap.put(skill.getId(), skill);
        this.skillCategoryMap.put(skill.getCategory(), skill);
        
        SkillType skillType = skill.getSkillConfig().getSkillType();
        if (SkillType.ACTIVE == skillType) {
            this.skillList.add(skill);
        }
    }

    @Override
    public void removeSkill(String skillCategory) {
        ISkill skill = this.skillCategoryMap.remove(skillCategory);
        if (skill != null) {
            this.skillMap.remove(skill.getId());
            this.skillList.remove(skill);
        }
    }

    @Override
    public ISkill getSkillByCategory(String skillCategory) {
        return this.skillCategoryMap.get(skillCategory);
    }

    @Override
    public ISkill getSkillById(String skillId) {
        return this.skillMap.get(skillId);
    }

    @Override
    public boolean isReadyForFire(ISkill skill) {
        if (skill == this.lastingSkill) {
            return true;
        }
        
        SkillStatus skillStatus = this.readyForFireSkillMap.get(skill.getCategory());
        return (skillStatus != null) && (System.currentTimeMillis() - skillStatus.getSkillFireTime() >= skill.getSkillConfig().getCasttime().intValue());
    }

    @Override
    public void setReadyForFire(ISkill skill) {
        SkillStatus skillStatus = new SkillStatus(skill, System.currentTimeMillis());
        
        this.readyForFireSkillMap.put(skill.getCategory(), skillStatus);
        
        //持续性技能
        if (skill.getSkillConfig().isLastingSkill()) {
            this.lastingSkill = skill;
        }
    }

    @Override
    public boolean skillWaveCheck(ISkill skill) {
        if (!this.readyForFireSkillMap.containsKey(skill.getCategory())) {
            return false;
        }
        
        SkillStatus skillStatus = this.readyForFireSkillMap.get(skill.getCategory());
        if (skillStatus.getRemainWaveNum() > 0) {
            skillStatus.setRemainWaveNum(skillStatus.getRemainWaveNum() - 1);
            return true;
        }
        
        this.readyForFireSkillMap.remove(skill.getCategory());
        return false;
    }

    @Override
    public long getRemainCd() {
        if (this.skillMap == null) {
            return 0;
        }
        
        long l1 = 0L;
        Iterator localIterator = this.skillList.iterator();
        while (localIterator.hasNext()) {
            ISkill localISkill = (ISkill) localIterator.next();
            long l2 = localISkill.remainCd();
            if (l1 == 0L) {
                l1 = l2;
            } else {
                l1 = l1 < l2 ? l1 : l2;
            }
        }
        return l1;
    }

    @Override
    public Collection<ISkill> getSkillList() {
        return this.skillMap.values();
    }

    @Override
    public ISkill getNextUseableSkill() {
        ISkill localISkill;
        for (int i = this.index; i < this.skillList.size(); i++) {
            localISkill = (ISkill) this.skillList.get(i);
            if (skillUseable(localISkill)) {
                this.index = i;
                return localISkill;
            }
        }
        for (int i = 0; i < this.index; i++) {
            localISkill = (ISkill) this.skillList.get(i);
            if (skillUseable(localISkill)) {
                this.index = i;
                return localISkill;
            }
        }
        return null;
    }

    private boolean skillUseable(ISkill skill) {
        return (skill.remainCd() <= 0L) && (SkillProcessHelper.skillCheck(skill, this.fighter)) && (!skill.hasLianZhao());
    }

    @Override
    public ISkill getNextFireSkill() {
        if ((this.nextFireSkill != null) && (this.nextFireSkill.remainCd() <= 0L)) {
            return this.nextFireSkill;
        }
        return null;
    }

    @Override
    public void setNextFireSkill(ISkill skill) {
        this.nextFireSkill = skill;
    }

    @Override
    public List<String> getSkillIds() {
        if ((this.skillList == null) || (this.skillList.size() == 0)) {
            return null;
        }
        return new ArrayList(this.skillMap.keySet());
    }

    @Override
    public List<String> getSkillMonsterIds() {
        return this.skillMonsterIds;
    }

    @Override
    public void addSkillMonsterIds(String paramString) {
        if (this.skillMonsterIds == null) {
            this.skillMonsterIds = new ArrayList();
        }
        this.skillMonsterIds.add(paramString);
    }

    @Override
    public void removeSkillCallMonster(String paramString) {
        if ((this.skillMonsterIds != null) && (this.skillMonsterIds.contains(paramString))) {
            this.skillMonsterIds.remove(paramString);
        }
    }

    @Override
    public Object[] getConfigSkillIds() {
        if ((this.skillCategoryMap == null) || (this.skillCategoryMap.size() == 0)) {
            return null;
        }
        return this.skillCategoryMap.keySet().toArray();
    }

    public class SkillStatus {
        
        private String skillCategory;
        private long skillFireTime;
        private int remainWaveNum;

        public SkillStatus(ISkill skill, long skillFireTime) {
            this.skillCategory = skill.getCategory();
            this.skillFireTime = skillFireTime;
            this.remainWaveNum = skill.getSkillConfig().getWaveNum();
        }

        public String getSkillCategory() {
            return this.skillCategory;
        }

        public void setSkillCategory(String skillCategory) {
            this.skillCategory = skillCategory;
        }

        public long getSkillFireTime() {
            return this.skillFireTime;
        }

        public void setSkillFireTime(long skillFireTime) {
            this.skillFireTime = skillFireTime;
        }

        public int getRemainWaveNum() {
            return this.remainWaveNum;
        }

        public void setRemainWaveNum(int remainWaveNum) {
            this.remainWaveNum = remainWaveNum;
        }
    }
}