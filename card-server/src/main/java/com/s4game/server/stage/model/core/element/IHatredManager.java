package com.s4game.server.stage.model.core.element;

import java.util.Collection;
import java.util.Map;

import com.s4game.server.stage.model.core.element.impl.hatred.HatredStatistic;
import com.s4game.server.stage.model.core.stage.ElementType;

public interface IHatredManager {
    
    public IHatred getHatredest();

    public HatredStatistic addActiveHatred(IFighter fighter, int paramInt1, int paramInt2);

    public HatredStatistic addPassiveHatred(IFighter fighter, int paramInt1, int paramInt2);

    public void refreshHatred();

    public void clear();

    public void addInsideHatred(Integer paramInteger, IFighter fighter);

    public IHatred getLastActiveAttackTarget();

    public boolean containsSpecificElementTypeHatred(ElementType paramElementType);

    public Map<String, Integer> getHarmMap();

    public int getHatredValByRole(IFighter fighter);

    public Collection<IHatred> getHatreds();
}
