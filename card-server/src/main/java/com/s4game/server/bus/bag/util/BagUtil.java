package com.s4game.server.bus.bag.util;

import com.s4game.server.bus.bag.constants.BagConstants;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public class BagUtil {
    
    public static boolean compareExpireTime(Long paramLong1, Long paramLong2) {
        if (paramLong1.equals(paramLong2)) {
            return true;
        }
        if ((paramLong1.equals(Long.valueOf(0L))) || (paramLong2.equals(Long.valueOf(0L)))) {
            return false;
        }
        long l = System.currentTimeMillis();
        if ((paramLong1.longValue() < l) && (paramLong2.longValue() < l)) {
            return true;
        }
        Long localLong1 = Long.valueOf(paramLong1.longValue() / 1000L);
        Long localLong2 = Long.valueOf(paramLong2.longValue() / 1000L);
        return localLong1.equals(localLong2);
    }

    public static boolean isBag(int slotNum) {
        return (BagConstants.BAG_MIN <= slotNum) && (BagConstants.BAG_MAX >= slotNum);
    }

    public static boolean isStorage(int slotNum) {
        return (BagConstants.STORAGE_MIN <= slotNum) && (BagConstants.STORAGE_MAX >= slotNum);
    }


    public static boolean checkSlotCanMove(int slotNum1, int slotNum2) {
        if ((isBag(slotNum1)) && (isBag(slotNum2))) {
            return true;
        }
        if ((isStorage(slotNum1)) && (isStorage(slotNum2))) {
            return true;
        }
        if ((isBag(slotNum1)) && (isStorage(slotNum2))) {
            return true;
        }
        if ((isStorage(slotNum1)) && (isBag(slotNum2))) {
            return true;
        }

        return (isBag(slotNum2));
    }
}