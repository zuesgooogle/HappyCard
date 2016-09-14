package com.s4game.server.stage.model.element.impl.buff;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.server.gamerule.goods.configure.export.IGoodsConfigService;
import com.s4game.server.gamerule.goods.configure.export.impl.GoodsConfig;
import com.s4game.server.stage.configure.export.IBuffConfigService;
import com.s4game.server.stage.configure.export.impl.BuffConfig;
import com.s4game.server.stage.model.core.element.IBuff;
import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.element.role.IRole;
import com.s4game.server.utils.id.IdUtil;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月27日 下午5:41:20
 *
 */
@Component
public class BuffFactory {

    private static final String BUFF = "buff";
    
    private static final String PROP_BUFF = "propbuff";
    
    @Resource
    private IBuffConfigService buffConfigService;
    
    @Resource
    private IGoodsConfigService goodsConfigService;
    
    public IBuff create(IFighter target, IFighter attacker, String buffId) {
        BuffConfig buffConfig = buffConfigService.loadById(buffId);
        if( null == buffConfig ) {
            return null;
        }
        
        String id = IdUtil.nextString(BUFF);
        Buff buff = new Buff(id, buffConfig);
        buff.setDeadRemoveOrNot(buffConfig.isDeadRemove());
        buff.setDuration(buffConfig.getLastTime());
        
        buff.setOwner(target);
        buff.setAttackerId(attacker.getId());
        
        if( null != buffConfig.getEffectPro() ) {
            buff.setAttributes(buffConfig.getEffectPro());
        }
        buff.setStateType(buffConfig.getEffectState());
        
        return buff;
    }
    
    public IBuff createPropBuff(IRole role, String goodsId) {
        GoodsConfig goodsConfig = goodsConfigService.loadById(goodsId);
        if( null == goodsConfig ) {
            return null;
        }
        
        BuffConfig buffConfig = buffConfigService.loadById(goodsConfig.getBuffId());
        if( null == buffConfig ) {
            return null;
        }
        
        String id = IdUtil.nextString(PROP_BUFF);
        PropBuff buff = new PropBuff(id, goodsConfig, buffConfig);
        buff.setBuffConfig(buffConfig);
        buff.setDeadRemoveOrNot(buffConfig.isDeadRemove());
        buff.setOwner(role);
        buff.setAttackerId(role.getId());
        
        if( null != buffConfig.getEffectPro() ) {
            buff.setAttributes(buffConfig.getEffectPro());
        }
        buff.setStateType(buffConfig.getEffectState());
        
        return buff;
    }
    
}
