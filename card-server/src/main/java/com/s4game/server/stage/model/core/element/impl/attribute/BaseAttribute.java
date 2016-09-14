package com.s4game.server.stage.model.core.element.impl.attribute;

import com.s4game.server.gamerule.attribute.AttributeType;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月24日 上午11:37:57
 *
 */

public final class BaseAttribute extends AbsBaseAttribute {

    @Override
    public void attributeCheck(String attr) {
        AttributeType.contains(attr);
    }

}
