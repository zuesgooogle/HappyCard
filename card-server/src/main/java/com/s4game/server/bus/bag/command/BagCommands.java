package com.s4game.server.bus.bag.command;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public class BagCommands {
    /**
     * 获取背包列表
     */
    public static final String GET_BAG_GOODS = "30000";
    
    /**
     * 清理背包，重置 bag slot num
     */
    public static final String CLEAR_UP = "30001";
    
    public static final String STORAGEGOODS = "30002";
    
    public static final String SPLIT_GOODS = "30100";
    public static final String DISCARD_GOODS = "30101";
    public static final String DELETE_GOODS = "30102";
    public static final String ACTIVATION_SLOT = "30201";
    public static final String BAG_SLOT_CZ = "30203";
    public static final String GOODS_UPDATE = "30104";
    public static final String GOODS_ADD = "30105";
    public static final String GOODS_ADD_EXTEND = "301050";
    public static final String INNER_DISCARD_GOODS = "S:DISCARD_GOODS";
    public static final String INNER_UPDATE_BAGSLOT_AMOUNT = "S:BAG_SLOT";
    public static final String HORN_CHAT = "11005";
    public static final String INNER_HORN_CHAT = "11005_1";
    public static final String CHANGE_CLOTHES = "31001";
    public static final String MOVE_GOODS = "30004";
    public static final String PROP_USE = "30301";
    public static final String GET_STORE_GOODS = "30002";
    public static final String STORAGE_CLEARUP = "30003";
    public static final String ACTIVATION_STORE_SLOT = "30202";
    public static final String STORAGE_SLOT_CZ = "30204";
    public static final String INNER_UPDATE_STORAGESLOT_AMOUNT = "S:STORAGE_SLOT";
    public static final String SELGOODS = "30302";
    public static final String GIFTCODE_AWARD = "GC:AWARD";
    public static final String USE_GIFTCODE = "57000";
    public static final String CLEAR_XUNBAO = "30006";
    public static final String GET_XUNBAO = "30005";
}
