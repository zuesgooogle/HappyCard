package com.s4game.server.stage.model.core.element.impl.attribute;

import java.util.HashMap;
import java.util.Map;

public class FightAttributeStatistic {
    
    private boolean hpMpChanged;
    
    private boolean attributeChanged;
    
    private boolean shanbiValChanged;
    
    private Map<Integer, Float> changedAttributeMap;

    public boolean isHpMpChanged() {
        return this.hpMpChanged;
    }

    public void changeHpOrMap() {
        this.hpMpChanged = true;
    }

    public void changeShanbiVal() {
        this.shanbiValChanged = true;
    }

    public boolean isAttributeChanged() {
        return this.attributeChanged;
    }

    public boolean isShanbiValChanged() {
        return this.shanbiValChanged;
    }

    public Map<Integer, Float> getChangedAttribute() {
        return this.changedAttributeMap;
    }

    public void changeAttribute(int attr, float value) {
        if (this.changedAttributeMap == null) {
            this.changedAttributeMap = new HashMap();
        }
        this.attributeChanged = true;
        this.changedAttributeMap.put(attr, value);
    }

    public void clear() {
        this.hpMpChanged = false;
        this.shanbiValChanged = false;
        if (this.attributeChanged) {
            this.attributeChanged = false;
            this.changedAttributeMap = null;
        }
    }
}