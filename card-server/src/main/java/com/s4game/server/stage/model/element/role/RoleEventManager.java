package com.s4game.server.stage.model.element.role;

import org.springframework.stereotype.Component;

import com.s4game.server.stage.model.core.element.IElement;
import com.s4game.server.stage.model.core.element.IElementEventManager;
import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.fight.IHarm;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月20日 下午7:07:19
 * 
 */
@Component
public class RoleEventManager implements IElementEventManager {

    private IRole role;
    
    public RoleEventManager() {
        
    }
    
    public RoleEventManager(IRole role) {
        this.role = role;
    }

    @Override
    public void fireLoginEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireLogoutEvent() {
        role.getId();
    }

    @Override
    public void fireMoveEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireHurtEvent(IHarm harm) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireAttackedEvent(IFighter fighter) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireFindElementEvent(IElement element) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireFirstAddHatredEvent(IFighter fighter) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireDeadEvent(IFighter fighter, IHarm harm) {
        // TODO Auto-generated method stub

    }

    @Override
    public void leaveFightStateEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireIncrActiveHatred(IFighter fighter, int paramInt1, int paramInt2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireIncrPassiveHatred(IFighter fighter, int paramInt1, int paramInt2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fireStateChange() {
        // TODO Auto-generated method stub

    }

}
