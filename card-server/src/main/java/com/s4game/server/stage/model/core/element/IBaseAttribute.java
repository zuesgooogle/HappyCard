package com.s4game.server.stage.model.core.element;

import java.util.Map;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月24日 上午11:24:33
 *
 */

public interface IBaseAttribute {
    
    public float get(String attr);
    
    public void set(String attr, float value);
    
    public Map<String, Float> toMap();

}
