package com.s4game.server.stage.model.core.stage;

import java.util.concurrent.TimeUnit;

public interface IElementProduceTeam {
    
    public void produce(int paramInt);

    public void scheduleProduce(int paramInt, TimeUnit timeUnit);

    public void reset();

    public void clear();

    public void retrieve(IStageElement stageElement);

    public int getLimit();

    public int getCount();

    public String getId();

    public String getElementId();

    public ElementType getElementType();

    public void setStage(IStage paramIStage);

    public void produceAll();

    public Long getNextRefreshTime();

    public void cancelSchedule();
}