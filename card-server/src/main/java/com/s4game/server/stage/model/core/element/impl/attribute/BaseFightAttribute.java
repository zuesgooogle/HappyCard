package com.s4game.server.stage.model.core.element.impl.attribute;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.s4game.server.stage.model.core.element.BaseAttributeType;
import com.s4game.server.stage.model.core.element.IBaseAttribute;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月24日 下午3:29:39
 * 
 */

public class BaseFightAttribute extends AbsFightAttribute {

    private Map<BaseAttributeType, IBaseAttribute> baseAttributeMap = new HashMap();

    private Map<BaseAttributeType, IBaseAttribute> noEffectAttributeMap = new HashMap();

    private IBaseAttribute effectAttribute;

    public BaseFightAttribute(AttributeFormulaSearcher attributeFormulaSearcher) {
        super(attributeFormulaSearcher);
    }

    private IBaseAttribute getAttribute(BaseAttributeType attributeType, boolean create) {
        IBaseAttribute baseAttribute = this.baseAttributeMap.get(attributeType);
        if (create && null == baseAttribute) {
            switch (attributeType) {
            case EFFECT:
                baseAttribute = new EffectAttribute();
                this.effectAttribute = baseAttribute;
                break;
            default:
                baseAttribute = new BaseAttribute();
                this.noEffectAttributeMap.put(attributeType, baseAttribute);
                break;
            }
            this.baseAttributeMap.put(attributeType, baseAttribute);
        }
        return baseAttribute;
    }

    @Override
    public void replaceBaseAttribute(BaseAttributeType baseAttributeType, Map<String, Float> map) {
        clearBaseAttribute(baseAttributeType, false);
        IBaseAttribute baseAttribute = getAttribute(baseAttributeType, true);

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();

            AttributeCalculateHelper.setAttribute(key, map.get(key), baseAttribute);
            addAttributeFormula(key);
        }
        refresh();
    }

    @Override
    public void incrBaseAttribute(BaseAttributeType baseAttributeType, Map<String, Float> map) {
        IBaseAttribute baseAttribute = getAttribute(baseAttributeType, true);
        
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();

            AttributeCalculateHelper.increaseAttribute(key, map.get(key), baseAttribute);
            addAttributeFormula(key);
        }
        refresh();
    }

    @Override
    public void descBaseAttribute(BaseAttributeType baseAttributeType, Map<String, Float> map) {
        IBaseAttribute baseAttribute = getAttribute(baseAttributeType, false);
        
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();

            AttributeCalculateHelper.decreaseAttribute(key, map.get(key), baseAttribute);
            addAttributeFormula(key);
        }
        refresh();
    }

    @Override
    public void clearBaseAttribute(BaseAttributeType baseAttributeType) {
        clearBaseAttribute(baseAttributeType, true);
    }

    private void clearBaseAttribute(BaseAttributeType baseAttributeType, boolean refresh) {
        IBaseAttribute baseAttribute = getAttribute(baseAttributeType, false);

        if (null != baseAttribute) {
            Map<String, Float> map = baseAttribute.toMap();
            if ((null != map) && !map.isEmpty()) {
                Iterator<Entry<String, Float>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, Float> entry = iterator.next();

                    AttributeCalculateHelper.setAttribute(entry.getKey(), 0, baseAttribute);
                    addAttributeFormula(entry.getKey());
                }
                if (refresh) {
                    refresh();
                }
            }
        }
    }
    
    @Override
    public float getNoEffectAttribute(String attr) {
        if( !this.noEffectAttributeMap.containsKey(attr) ) {
            return 0;
        }
        
        float total = 0;
        for (IBaseAttribute baseAttribute : this.noEffectAttributeMap.values()) {
            total += baseAttribute.get(attr);
        }
        
        return total;
    }

    @Override
    public float getEffectAttribute(String attr) {
        return null != this.effectAttribute ? this.effectAttribute.get(attr) : 0;
    }

}
