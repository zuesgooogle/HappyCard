package com.s4game.server.bus.skill.configure.export.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.s4game.server.bus.skill.configure.export.ISkillConfigExportService;
import com.s4game.server.configure.parser.impl.AbsClasspathConfigureParser;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月4日 下午3:52:28
 *
 */
@Component
public class SkillConfigExportServiceImpl extends AbsClasspathConfigureParser implements ISkillConfigExportService {

    @Override
    public SkillConfig loadById(String skillId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkillConfig loadSkillByLevel(String skillId, int level) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getSubsequentSkillCategorys(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getResistSkill(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getResistBuffSkill(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkillLianConfig loadSkillLianById(String paramString) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void configureDataResolve(byte[] bytes) {
        // TODO Auto-generated method stub

    }

    @Override
    protected String getConfigureName() {
        return "skill.dat";
    }

}
