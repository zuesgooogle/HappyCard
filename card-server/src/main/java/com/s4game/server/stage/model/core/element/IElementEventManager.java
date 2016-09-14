package com.s4game.server.stage.model.core.element;

import com.s4game.server.stage.model.core.fight.IHarm;

public interface IElementEventManager {
    
    public static final String EVENTTYPE_LOGIN = "LOGIN";
    public static final String EVENTTYPE_LOGOUT = "LOGOUT";
    public static final String EVENTTYPE_MOVE = "MOVE";
    public static final String EVENTTYPE_ATTACKED = "ATTACKED";
    public static final String EVENTTYPE_FINDELEMENT = "FINDELEMENT";

    public void fireLoginEvent();

    public void fireLogoutEvent();

    public void fireMoveEvent();

    public void fireHurtEvent(IHarm harm);

    public void fireAttackedEvent(IFighter fighter);

    public void fireFindElementEvent(IElement element);

    public void fireFirstAddHatredEvent(IFighter fighter);

    public void fireDeadEvent(IFighter fighter, IHarm harm);

    public void leaveFightStateEvent();

    public void fireIncrActiveHatred(IFighter fighter, int paramInt1, int paramInt2);

    public void fireIncrPassiveHatred(IFighter fighter, int paramInt1, int paramInt2);

    public void fireStateChange();
}