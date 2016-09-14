package com.s4game.server.stage.model.element.impl.buff;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.s4game.server.stage.configure.export.impl.BuffConfig;
import com.s4game.server.stage.model.core.element.IBuff;
import com.s4game.server.stage.model.core.element.IFighter;
import com.s4game.server.stage.model.core.element.IState;
import com.s4game.server.stage.model.core.element.impl.state.StateType;
import com.s4game.server.stage.model.element.impl.state.BuffStateFactory;
import com.s4game.server.stage.utils.AttributeUtil;

public class Buff implements IBuff {

    private String id;
    private String category;
    private String buffId;
    private int level;
    private int layer = 1;
    private long startTime;
    private long duration;
    
    //private int period = -1;
    private String specialEffect;
    //private boolean firstTriggered = false;
    
    private String attackerId;
    private IFighter owner;
    private Map<String, Float> attributes;
    private Integer stateType;
    private Object additionalData;
    private boolean deadRemoveOrNot;
    
    //private String specialId;

    private BuffConfig buffConfig;
    
    public Buff(String id, BuffConfig buffConfig) {
        this.id = id;
        this.buffConfig = buffConfig;
        this.category = buffConfig.getEffectTypeID();
        this.level = buffConfig.getEffectLevel();
        this.buffId = buffConfig.getEffectId();
        
        this.buffConfig = buffConfig;
    }

    public String getBuffId() {
        return this.buffId;
    }

    public String getBuffCategory() {
        return this.category;
    }

    public Integer getLevel() {
        return this.level;
    }

    public int getLayer() {
        return this.layer;
    }

    public String getId() {
        return this.id;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void start() {
        if ((null != this.attributes) && !this.attributes.isEmpty()) {
            for (int i = 0; i < this.layer; i++) {
                AttributeUtil.incrBuffAttribute(this.owner, this.attributes);
            }
        }
        
        if (null != this.stateType) {
            IState state = BuffStateFactory.create(this.stateType);
            if (null != state) {
                this.owner.getStateManager().add(state);
            }
        }
        
        this.startTime = System.currentTimeMillis();
        
        if (this.duration != 0L) {
            Object data = new Object[] { getBuffCategory(), getId(), owner.getId(), owner.getElementType().getVal(), owner.getStage().getId() };
            
            String stageId = owner.getStage().getId();
            this.owner.getScheduleManager().schedule("stage", owner.getId(), stageId, getId(), "BUFF:END", data, duration, TimeUnit.MILLISECONDS);
        }
        scheduleSpecialEffectTrigger();
    }

    public void scheduleSpecialEffectTrigger() {
        if (null != this.specialEffect) {
//            Object localObject;
//            if (!this.firstTriggered) {
//                localObject = SpecialEffectHandlerFactory.getHandler(this.specialEffect);
//                ((ISpecialEffectHandler) localObject).triggerHandle(this);
//                this.firstTriggered = true;
//            }
//            if (this.period > 0) {
//                localObject = new Object[] { getBuffCategory(), getId(), this.owner.getId(), this.owner.getElementType().getVal(),
//                        this.owner.getStage().getId() };
//                this.owner.getScheduleManager().schedule("stage", this.owner.getId(), this.owner.getStage().getId(), getSpecialId(), "BUFF:TRIGGER",
//                        localObject, this.period, TimeUnit.MILLISECONDS);
//            }
        }
    }

    public void end() {
        this.owner.getScheduleManager().cancelSchedule("stage", getId());
        if (null != this.attributes) {
            for (int i = 0; i < this.layer; i++) {
                AttributeUtil.decrBuffAttribute(this.owner, this.attributes);
            }
        }

        if (null != this.stateType) {
            StateType stateType = BuffStateFactory.getType(this.stateType);
            if (null != stateType) {
                this.owner.getStateManager().remove(stateType);
            }
        }
        
//        if (null != this.specialEffect) {
//            localObject = SpecialEffectHandlerFactory.getHandler(this.specialEffect);
//            ((ISpecialEffectHandler) localObject).overHandle(this);
//            this.owner.getScheduleManager().cancelSchedule("stage", getSpecialId());
//        }
    }

    public void setOwner(IFighter owner) {
        this.owner = owner;
    }

    public void setAttributes(Map<String, Float> attributes) {
        this.attributes = attributes;
    }

    public void setAttackerId(String attackerId) {
        this.attackerId = attackerId;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

//    public void setSpecialPeriod(int period) {
//        this.period = period;
//    }

    public String getAttackerId() {
        return this.attackerId;
    }

    public void setSpecialEffect(String specialEffect) {
        this.specialEffect = specialEffect;
    }

    public String getSpecialEffect() {
        return this.specialEffect;
    }

    public Long getStartTime() {
        return Long.valueOf(this.startTime);
    }

    public <T> T getAdditionalData() {
        return (T) this.additionalData;
    }

    public void setAdditionalData(Object additionalData) {
        this.additionalData = additionalData;
    }

    public Object getClientMsg() {
        return new Object[] { getId(), getBuffId(),
                Long.valueOf(this.duration > 0L ? this.duration - (int) (System.currentTimeMillis() - this.startTime) : -1L), Integer.valueOf(getLayer()),
                getAttackerId() };
    }

    public void setStateType(int stateType) {
        this.stateType = stateType;
    }

    public void setDeadRemoveOrNot(boolean deadRemoveOrNot) {
        this.deadRemoveOrNot = deadRemoveOrNot;
    }

    public boolean isDeadRemoveOrNot() {
        return this.deadRemoveOrNot;
    }

    public long getDuration() {
        return this.duration;
    }

    public IFighter getOwner() {
        return this.owner;
    }

    public boolean isProtected() {
        return this.buffConfig.isProtected();
    }

    public BuffConfig getBuffConfig() {
        return this.buffConfig;
    }

    public void setBuffConfig(BuffConfig buffConfig) {
        this.buffConfig = buffConfig;
    }

//    private String getSpecialId() {
//        if (this.specialId == null) {
//            this.specialId = (getId() + "-s");
//        }
//        return this.specialId;
//    }

}