package com.s4game.server.bus.map;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月17日 上午10:15:50
 * 
 */

public class MapType {

    public static final int NORMAL = 1;

    public static final int ZHUCHENG = 2;

    public static final int CHALLENGE = 3;

    /**
     * 关卡
     */
    public static final int CHECK_POINT = 4;
    
    public static boolean isCopy(int type) {
        return CHALLENGE == type || CHECK_POINT == type;
    }

    public static boolean usedForOfflineSave(int type) {
        return (NORMAL == type) || (ZHUCHENG == type);
    }

}
