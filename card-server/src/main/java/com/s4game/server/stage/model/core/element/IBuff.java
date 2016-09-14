package com.s4game.server.stage.model.core.element;

import com.s4game.server.stage.configure.export.impl.BuffConfig;

public interface IBuff {
    
    public String getId();

    public String getBuffId();

    public String getBuffCategory();

    public Integer getLevel();

    public int getLayer();

    public void start();

    public void end();

    public void setLayer(int layer);

    public void scheduleSpecialEffectTrigger();

    public String getAttackerId();

    public String getSpecialEffect();

    public Long getStartTime();

    public <T> T getAdditionalData();

    public Object getClientMsg();

    public void setStateType(int stateType);

    public boolean isDeadRemoveOrNot();

    public long getDuration();

    public IFighter getOwner();

    public boolean isProtected();

    public void setDuration(long duration);

    public BuffConfig getBuffConfig();
}
