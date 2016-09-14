package com.s4game.server.stage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.server.stage.model.core.element.IBuff;
import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.stage.ElementType;
import com.s4game.server.stage.model.core.stage.IStage;
import com.s4game.server.stage.model.element.impl.buff.BuffFactory;
import com.s4game.server.stage.model.element.role.IRole;
import com.s4game.server.stage.service.IBuffService;
import com.s4game.server.stage.service.IStageService;
import com.s4game.server.stage.swap.StageMsgQueue;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月27日 下午6:21:41
 *
 */
@Component
public class BuffServiceImpl implements IBuffService {

    @Resource
    private IStageService stageService;
    
    @Resource
    private BuffFactory buffFactory;
    
    @Override
    public void addBuff(String stageId, String roleId, String buffId, StageMsgQueue stageMsgQueue) {
        IStage stage = stageService.getStage(stageId);
        IRole role = stage.getElement(roleId, ElementType.ROLE);
        IBuff buff = buffFactory.create(role, role, buffId);
        
        role.getBuffManager().addBuff(buff);
        //TODO output buff change...
    }

    @Override
    public void addPropBuff(String stageId, String roleId, String goodsId, StageMsgQueue stageMsgQueue) {
        IStage stage = stageService.getStage(stageId);
        IRole role = stage.getElement(roleId, ElementType.ROLE);
        
        IBuff buff = buffFactory.createPropBuff(role, goodsId);
        
        role.getBuffManager().addBuff(buff);
        //TODO output buff change...
    }
    
    @Override
    public void endBuff(String stageId, String elementId, int elementType, String id, String category) {
        IStage stage = stageService.getStage(stageId);
        IFighter fighter = (IFighter)stage.getElement(elementId, ElementType.convert(elementType));
        
        if( null != fighter ) {
            fighter.getBuffManager().removeBuff(id, category);
            //TODO output buff change...
        }
    }

    @Override
    public void stopBuff(String stageId, String roleId, String buffId) {
        IStage stage = stageService.getStage(stageId);
        IRole role = stage.getElement(roleId, ElementType.ROLE);
        
        IBuff buff = role.getBuffManager().getBuffById(buffId);
        if( null == buff ) {
            return;
        }
        
        role.getBuffManager().removeBuff(buff.getId(), buff.getBuffCategory());
        
        //TODO ouput buff change
    }
}
