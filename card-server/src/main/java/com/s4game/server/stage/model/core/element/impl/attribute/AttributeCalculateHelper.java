package com.s4game.server.stage.model.core.element.impl.attribute;

import com.s4game.server.stage.model.core.element.IBaseAttribute;

public class AttributeCalculateHelper {
    
    public static void increaseAttribute(String attr, float value, IBaseAttribute baseAttribute) {
        float old = baseAttribute.get(attr);

        baseAttribute.set(attr, old + value);
    }

    public static void setAttribute(String attr, float value, IBaseAttribute baseAttribute) {
        baseAttribute.set(attr, value);
    }

    public static void decreaseAttribute(String attr, float value, IBaseAttribute baseAttribute) {
        float old = baseAttribute.get(attr);
        old -= value;
        
        if ( old < 0 ) {
            old = 0;
        }
        baseAttribute.set(attr, old);
    }
}