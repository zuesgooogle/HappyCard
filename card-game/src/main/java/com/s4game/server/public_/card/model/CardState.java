package com.s4game.server.public_.card.model;

import com.s4game.core.enum_.EnumUtils;
import com.s4game.core.enum_.IntEnum;

/**
 * 状态
 * 
 * @author zeusgooogle@gmail.com
 * @sine 2016年10月9日 下午8:35:07
 */
public enum CardState implements IntEnum {

    /**
     * 手中
     */
    HAND_CARD(1),

    /**
     * 打出的，吃，碰，别人可以看到的
     */
    OPEN_CARD(2),

    /**
     * 坎，别人看不见得
     */
    CLOSE_CARD(3),

    /**
     * 弃牌
     */
    DISCARD(4),
    
    
    ;

    private static CardState[] INDEXS = EnumUtils.toArray(values());

    private final int type;

    private CardState(int type) {
        this.type = type;
    }

    @Override
    public int getId() {
        return type;
    }

    public static CardState findById(int value) {
        if (value < 0 || value >= INDEXS.length) {
            return null;
        }
        return INDEXS[value];
    }
}
