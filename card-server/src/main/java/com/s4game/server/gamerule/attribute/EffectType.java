package com.s4game.server.gamerule.attribute;

import java.util.ArrayList;
import java.util.List;

public class EffectType {
    
    public static final String X1 = "1";
    public static final String X2 = "2";
    public static final String X3 = "3";
    public static final String X4 = "4";
    public static final String X5 = "5";
    public static final String X6 = "6";
    public static final String X7 = "7";
    public static final String X8 = "8";
    public static final String X9 = "9";
    public static final String X10 = "10";
    public static final String X11 = "11";
    public static final String X12 = "12";
    public static final String X13 = "13";
    public static final String X14 = "14";
    public static final String X15 = "15";
    public static final String X16 = "16";
    public static final String X17 = "17";
    public static final String X18 = "18";
    public static final String X19 = "19";
    public static final String X20 = "20";
    public static final String X101 = "101";
    public static final String X102 = "102";
    public static final String X103 = "103";
    public static final String X104 = "104";
    public static final String X121 = "121";
    public static final String X122 = "122";
    public static final String X123 = "123";
    public static final String X124 = "124";
    public static final String X125 = "125";
    public static final String X126 = "126";
    public static final String X131 = "131";
    public static final String X132 = "132";
    public static final String X133 = "133";
    public static final String X134 = "134";
    public static final String X135 = "135";
    public static final String X136 = "136";
    public static final String X137 = "137";
    
    private static List<String> effects = new ArrayList();

    public static void contains(String type) {
        if (!effects.contains(type)) {
            throw new RuntimeException("exclude this effectType: " + type);
        }
    }

    static {
        effects.add("1");
        effects.add("2");
        effects.add("3");
        effects.add("4");
        effects.add("5");
        effects.add("6");
        effects.add("7");
        effects.add("8");
        effects.add("9");
        effects.add("10");
        effects.add("11");
        effects.add("12");
        effects.add("13");
        effects.add("14");
        effects.add("15");
        effects.add("16");
        effects.add("17");
        effects.add("18");
        effects.add("19");
        effects.add("20");
        effects.add("101");
        effects.add("102");
        effects.add("103");
        effects.add("104");
        effects.add("121");
        effects.add("122");
        effects.add("123");
        effects.add("124");
        effects.add("125");
        effects.add("126");
        effects.add("131");
        effects.add("132");
        effects.add("133");
        effects.add("134");
        effects.add("135");
        effects.add("136");
        effects.add("137");
    }
}