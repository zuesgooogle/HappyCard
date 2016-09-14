package com.s4game.server.bus.xunbao.util;

import java.util.Random;

public class RandomUtil {
    
    public static final int RANDOM_UP = 100;

    public static int getIntRandomValue(int paramInt) {
        if (paramInt == 0) {
            return paramInt;
        }
        return new Random().nextInt(paramInt);
    }

    public static float getFloatRandom() {
        return new Random().nextFloat();
    }
}