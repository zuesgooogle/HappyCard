package com.s4game.server.bus.skill.configure.export;

import java.util.List;

import com.s4game.server.bus.skill.configure.export.impl.SkillConfig;
import com.s4game.server.bus.skill.configure.export.impl.SkillLianConfig;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月4日 下午3:48:05
 *
 */

public interface ISkillConfigExportService {

    //public SkillCategoryConfig loadByCategory(String paramString);
    
    public SkillConfig loadById(String skillId);
    
    public SkillConfig loadSkillByLevel(String skillId, int level);
    
    //public SkillInfoConfig loadSkillInfoByLevel(String paramString, Integer paramInteger);
    
    //public SkillInfoConfig loadSkillInfoBySkillId(String paramString);
    
    public List<String> getSubsequentSkillCategorys(String paramString);
    
    public String getResistSkill(String paramString);
    
    public String getResistBuffSkill(String paramString);
    
    public SkillLianConfig loadSkillLianById(String paramString);
}
