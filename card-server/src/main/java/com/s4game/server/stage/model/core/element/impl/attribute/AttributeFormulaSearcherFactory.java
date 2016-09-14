package com.s4game.server.stage.model.core.element.impl.attribute;


/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月24日 下午4:16:47
 * 
 */

public class AttributeFormulaSearcherFactory {

    private static AttributeFormulaSearcher searcher = new AttributeFormulaSearcher();

    public static AttributeFormulaSearcher get() {
        return searcher;
    }
    
    static {
        
    }
}
