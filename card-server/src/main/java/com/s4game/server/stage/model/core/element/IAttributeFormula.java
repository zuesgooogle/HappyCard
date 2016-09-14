package com.s4game.server.stage.model.core.element;

import com.s4game.server.stage.model.core.element.impl.attribute.AbsFightAttribute;
import com.s4game.server.stage.model.core.element.impl.attribute.FightAttributeWriter;

public interface IAttributeFormula {
    
    public void refreshAttribute(AbsFightAttribute fightAttribute, FightAttributeWriter fightAttributeWriter);

    public void addRelateFormula(IAttributeFormula attributeFormula);
}
