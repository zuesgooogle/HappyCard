package com.s4game.server.stage.model.element.role;

import com.s4game.server.stage.model.core.element.impl.attribute.AttributeFormulaSearcher;
import com.s4game.server.stage.model.core.element.impl.attribute.BaseFightAttribute;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月24日 下午4:12:26
 *
 */

public class RoleFightAttribute extends BaseFightAttribute implements IRoleFightAttribute {

    //private int tiLi;
    
    public RoleFightAttribute(AttributeFormulaSearcher attributeFormulaSearcher) {
        super(attributeFormulaSearcher);
    }

    @Override
    public void incrTiLi(int tili) {

    }

    @Override
    public void decrTiLi(int tili) {

    }

    @Override
    public int getTiLi() {
        return 0;
    }

}
