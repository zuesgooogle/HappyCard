package com.s4game.server.stage.service;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月4日 下午3:13:49
 *
 */

public interface IFightService {

    /**
     * 技能释放准备
     * 
     * @param stageId
     * @param attacker
     * @param target
     * @param skillCategory
     */
    public void skillReadyFire(String stageId, String roleId, String attacker, String skillCategory);
    
    /**
     * 技能释放
     * 
     * @param stageId
     * @param roleId
     * @param attacker
     * @param skillCategory
     * @param targets          技能目标
     */
    public void skillFire(String stageId, String roleId, String attacker, String skillCategory, String[] targets);
    
}
