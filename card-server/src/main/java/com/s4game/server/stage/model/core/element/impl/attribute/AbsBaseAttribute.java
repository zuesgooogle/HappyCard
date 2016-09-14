package com.s4game.server.stage.model.core.element.impl.attribute;

import java.util.HashMap;
import java.util.Map;

import com.s4game.server.stage.model.core.element.IBaseAttribute;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月24日 上午11:31:41
 * 
 */

public abstract class AbsBaseAttribute implements IBaseAttribute {
    
    private Map<String, Float> attributes = null;

    @Override
    public float get(String attr) {
        attributeCheck(attr);
        if (null == this.attributes) {
            return 0;
        }
        if (null == this.attributes.get(attr)) {
            return 0;
        }
        return this.attributes.get(attr);
    }

    @Override
    public void set(String attr, float value) {
        attributeCheck(attr);
        if (null == this.attributes) {
            this.attributes = new HashMap();
        }
        this.attributes.put(attr, value);
    }

    public void add(String attr, float value) {
        attributeCheck(attr);
        if ((null != this.attributes) && (this.attributes.containsKey(attr))) {
            this.attributes.put(attr, this.attributes.get(attr) + value);
        }
    }

    public Map<String, Float> toMap() {
        if (null == this.attributes) {
            return null;
        }
        return this.attributes;
    }

    public abstract void attributeCheck(String attr);
    
}
