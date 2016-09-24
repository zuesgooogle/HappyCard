package com.s4game.server.public_.card.model;

import com.s4game.core.enum_.EnumUtils;
import com.s4game.core.enum_.IntEnum;

/**
*
* @author zeusgoogogle@gmail.com
* @sine 2016年9月24日 下午8:39:40
*
*/
public enum CardAction implements IntEnum {
	
	/**
	 * 过
	 */
	PASS(1),
	
	/**
	 * 吃
	 */
	CHI_PAI(2),
	
	/**
	 * 碰
	 */
	PENG_PAID(4),
	
	/**
	 * 提龙
	 */
	TI_LONG(8),
	
	/**
	 * 胡牌
	 */
	HU_PAI(16),
	
	;
	
	private static CardAction[] INDEXS = EnumUtils.toArray(values());
	
	private final int id;
	
	private CardAction(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}
	
	public static CardAction findById(int value) {
        if (value < 0 || value >= INDEXS.length) {
            return null;
        }
        return INDEXS[value];
    }
}
