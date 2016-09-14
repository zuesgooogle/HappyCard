package com.s4game.server.gamerule.goods.util;

import org.springframework.stereotype.Component;

@Component
public class GoodsCategoryUtil {
    
    /**
     *  物品排序类型
     */
    public static int GOODS_ORDER_SIZE = 10;
    
    public static final int EQUIP_TYPE = 20;
   


    public static final boolean isEquip(int category) {
        return category == EQUIP_TYPE;
    }

}
