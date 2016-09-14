package com.s4game.server.stage.service;

import com.s4game.server.stage.swap.StageMsgQueue;
import com.s4game.server.stage.configure.export.impl.BuffConfig;

import com.s4game.server.stage.model.element.impl.buff.Buff;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月27日 下午6:14:43
 *
 */

public interface IBuffService {

    /**
     * Add Buff 
     * 
     * @param stageId
     * @param roleId
     * @param buffId {@link BuffConfig#getEffectId()}   
     * 
     * @param stageMsgQueue
     */
    public void addBuff(String stageId, String roleId, String buffId, StageMsgQueue stageMsgQueue);
    
    /**
     * Add Buff by goods
     * 
     * @param stageId
     * @param roleId
     * @param goodsId
     * @param stageMsgQueue
     */
    public void addPropBuff(String stageId, String roleId, String goodsId, StageMsgQueue stageMsgQueue);
    
    /**
     * 
     * @param stageId
     * @param elementId
     * @param elementType
     * @param id    {@link Buff#getId()}
     * @param category {@link Buff#getBuffCategory()}
     */
    public void endBuff(String stageId, String elementId, int elementType, String id, String category);
    
    /**
     * Stop Buff
     * 
     * @param stageId
     * @param roleId
     * @param buffId {@link BuffConfig#getEffectId()}
     */
    public void stopBuff(String stageId, String roleId, String buffId);
}
