package com.s4game.server.stage.model.core.element.impl.attribute;

import java.util.HashMap;
import java.util.Map;

import com.s4game.server.stage.model.core.element.IAttributeFormula;

public class AttributeFormulaSearcher
{
    private Map<String, IAttributeFormula> formulas = new HashMap();

    public void addFormulas(String attr, IAttributeFormula attributeFormula) {
        this.formulas.put(attr, attributeFormula);
    }

    public IAttributeFormula findReference(String attr) {
        return (IAttributeFormula) this.formulas.get(attr);
    }
}
