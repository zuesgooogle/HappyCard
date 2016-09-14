package com.s4game.server.stage.model.core.element;

import java.util.Collection;

import com.s4game.server.stage.model.element.impl.buff.BuffStatistics;

public interface IBuffManager extends IElementComponent {
    
    public void addBuff(IBuff buff);

    public void recoverBuff(IBuff buff);

    public void removeBuff(String id, String category);

    public IBuff getBuff(String id, String category);

    public IBuff getBuff(String category);

    public Collection<IBuff> getBuffs();

    public void startReadyForRecoveredBuffsAll();

    public void clearBuffsByDead();

    public void clearAll();

    public Object getBuffClientMsgs();

    public IBuff getBuffById(String buffId);

    public BuffStatistics getBuffStatistics();
}
