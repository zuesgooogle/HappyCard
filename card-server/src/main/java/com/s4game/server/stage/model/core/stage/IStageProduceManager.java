package com.s4game.server.stage.model.core.stage;

import java.util.Collection;

public interface IStageProduceManager {
    
    public Collection<IElementProduceTeam> getElementProduceTeams(ElementType elementType);

    public IElementProduceTeam getElementProduceTeam(ElementType elementType, String id);

    public void addElementProduceTeam(IElementProduceTeam elementProduceTeam);

    public void produceAll();

    public void cancelAllSchedule();
}
