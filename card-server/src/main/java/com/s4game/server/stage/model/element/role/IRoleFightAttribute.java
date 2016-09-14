package com.s4game.server.stage.model.element.role;

import com.s4game.server.stage.model.core.element.IFightAttribute;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月21日 下午6:19:58
 * 
 */

public interface IRoleFightAttribute extends IFightAttribute {

    public void incrTiLi(int tili);

    public void decrTiLi(int tili);

    public int getTiLi();

}
