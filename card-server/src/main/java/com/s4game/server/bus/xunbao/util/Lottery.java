package com.s4game.server.bus.xunbao.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum Lottery {
    
    TEN(10), 
    
    HUNDRED(100), 
    
    THOUSAND(1000), 
    
    TENTHOUSAND(10000);

    private int val;

    private Lottery(int val) {
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }

    public static int roll(int paramInt) {
        return new Random().nextInt(paramInt);
    }

    public static boolean isSuccess(float paramFloat) {
        return new Random().nextFloat() < paramFloat;
    }

    public static int randomInt(int paramInt1, int paramInt2) {
        if (paramInt1 == paramInt2) {
            return paramInt1;
        }
        return paramInt1 + new Random().nextInt(paramInt2 - paramInt1);
    }

    public static float randomFloat(float paramFloat1, float paramFloat2, Lottery paramLottery) {
        int i = (int) (paramFloat1 * paramLottery.getVal());
        int j = (int) (paramFloat2 * paramLottery.getVal());
        int k = randomInt(i, j);
        return k / (paramLottery.getVal() * 1.0F);
    }

    public static <T, V> T randomMapKey(Map<T, V> paramMap) {
        ArrayList localArrayList = new ArrayList(paramMap.keySet());
        int i = roll(localArrayList.size());
        return (T) localArrayList.get(i);
    }

    public static boolean roll(float paramFloat, Lottery paramLottery) {
        int i = (int) (paramFloat * paramLottery.getVal());
        return (paramFloat > 0.0F) && (roll(paramLottery.getVal()) <= i);
    }

    public static int rollInt(float paramFloat, Lottery paramLottery) {
        int i = (int) (paramFloat * paramLottery.getVal());
        return roll(i);
    }

    public static <T> T getRandomKey(Map<T, Float> paramMap) {
        float f1 = 0.0F;
        float f2 = RandomUtil.getFloatRandom();
        Iterator localIterator = paramMap.entrySet().iterator();
        while (localIterator.hasNext()) {
            Map.Entry localEntry = (Map.Entry) localIterator.next();
            if ((f2 >= f1) && (f2 < f1 + ((Float) localEntry.getValue()).floatValue())) {
                return (T) localEntry.getKey();
            }
            f1 += ((Float) localEntry.getValue()).floatValue();
        }
        return null;
    }

    public static <T> T getRandomKey2(Map<T, Float> paramMap) {
        if ((paramMap == null) || (paramMap.size() == 0)) {
            return null;
        }
        float f1 = 0.0F;
        Object localObject1 = paramMap.entrySet().iterator();
        Object localObject2;
        while (((Iterator) localObject1).hasNext()) {
            localObject2 = (Map.Entry) ((Iterator) localObject1).next();
            f1 += ((Float) ((Map.Entry) localObject2).getValue()).floatValue();
        }
        if (f1 == 0.0F) {
            localObject1 = paramMap.keySet();
            localObject2 = new ArrayList((Collection) localObject1);
            return (T) ((List) localObject2).get(roll(((List) localObject2).size()));
        }
        float f2 = 0.0F;
        int i = rollInt(f1, TENTHOUSAND);
        Iterator localIterator = paramMap.entrySet().iterator();
        while (localIterator.hasNext()) {
            Map.Entry localEntry = (Map.Entry) localIterator.next();
            float f3 = f2 + ((Float) localEntry.getValue()).floatValue();
            if ((i >= f2 * TENTHOUSAND.getVal()) && (i < f3 * TENTHOUSAND.getVal())) {
                return (T) localEntry.getKey();
            }
            f2 = f3;
        }
        return null;
    }

    public static void main(String[] paramArrayOfString) {
        HashMap localHashMap = new HashMap();
        for (int i = 0; i < 20; i++) {
            System.out.println((String) getRandomKey2(localHashMap));
        }
    }
}