package com.s4game.server.gamerule.attribute;

import java.util.ArrayList;
import java.util.List;

public class AttributeType {
    public static final String ZhuangBeiWuLiGongJi = "eqphysicdamage";
    public static final String ZhuangBeiFaShuGongJi = "eqmagicdamage";
    public static final String ZhuangBeiWuLiFangYu = "eqphysicdefence";
    public static final String ZhuangBeiFaShuFangYu = "eqmagicdefence";
    public static final String LiLiang = "strengh";
    public static final String ZhiLi = "intelligence";
    public static final String NaiLi = "vitality";
    public static final String JingShen = "spirit";
    public static final String ShenFa = "velocity";
    public static final String GongJi = "physicdamage";
    public static final String FaShuGongJi = "magicdamage";
    public static final String FangYu = "physicdefence";
    public static final String FaShuFangYu = "magicdefence";
    public static final String MaxHp = "hitpoints";
    public static final String MaxMp = "mana";
    public static final String BaoJi = "critical";
    public static final String RenXing = "criticaldefence";
    public static final String BiSha = "criticalb";
    public static final String BiHu = "criticalbdefence";
    public static final String MingZhong = "tohit";
    public static final String ShanBi = "volt";
    public static final String GeDang = "dodge";
    public static final String PoJi = "dodgedefence";
    public static final String WuLiChuanCi = "ignorephysicdefence";
    public static final String FaShuChuanCi = "ignoremagicdefence";
    public static final String HunMiDiKang = "stunresist";
    public static final String BingDongDiKang = "freezeresist";
    public static final String ShiHuaDiKang = "petrifactionresist";
    public static final String BianXingDiKang = "transmutationresist";
    public static final String JianSuDiKang = "slowresist";
    public static final String ZuZhouDiKang = "curseresist";
    public static final String GongJiSuDu = "attackrate";
    public static final String MB = "mb";
    public static final String MBDK = "mbdk";
    public static final String YX = "yx";
    public static final String YXDK = "yxdk";
    public static final String BH = "bh";
    public static final String BHDK = "bhdk";
    public static final String XX = "xx";
    public static final String XXDK = "xxdk";
    public static final String MaxShanbiVal = "dunshudian";
    public static final String CRITICALB = "criticalb";
    public static final String CRITICALC = "criticalc";
    public static final String POJIA = "pojia";
    public static final String QIEGE = "qiege";
    public static final String JIANGU = "jiangu";
    public static final String SHISHEN = "shishen";
    public static final String SHENYOU = "shenyou";
    public static final String DAMAGE = "damage";
    public static final String DEFENCE = "defence";
    public static final String PERDAMAGE = "perdamage";
    public static final String REDAMAGE = "redamage";
    public static final String REPH = "reph";
    public static final String LEIDAMAGE = "leidamage";
    public static final String LEIDEFENCE = "leidefence";
    public static final String MUDAMAGE = "mudamage";
    public static final String MUDEFENCE = "mudefence";
    public static final String SHUIDAMAGE = "shuidamage";
    public static final String SHUIDEFENCE = "shuidefence";
    public static final String HUODAMAGE = "huodamage";
    public static final String HUODEFENCE = "huodefence";
    public static final String TUDAMAGE = "tudamage";
    public static final String TUDEFENCE = "tudefence";
    
    private static List<String> attributes = new ArrayList();

    public static void contains(String attr) {
        if (!attributes.contains(attr)) {
            throw new RuntimeException("exclude this attribute: " + attr);
        }
    }

    static {
        attributes.add("leidamage");
        attributes.add("leidefence");
        attributes.add("mudamage");
        attributes.add("mudefence");
        attributes.add("shuidamage");
        attributes.add("shuidefence");
        attributes.add("huodamage");
        attributes.add("huodefence");
        attributes.add("tudamage");
        attributes.add("tudefence");
        attributes.add("eqphysicdamage");
        attributes.add("eqmagicdamage");
        attributes.add("eqphysicdefence");
        attributes.add("eqmagicdefence");
        attributes.add("strengh");
        attributes.add("intelligence");
        attributes.add("vitality");
        attributes.add("spirit");
        attributes.add("velocity");
        attributes.add("physicdamage");
        attributes.add("magicdamage");
        attributes.add("physicdefence");
        attributes.add("magicdefence");
        attributes.add("hitpoints");
        attributes.add("mana");
        attributes.add("critical");
        attributes.add("criticaldefence");
        attributes.add("criticalb");
        attributes.add("criticalbdefence");
        attributes.add("tohit");
        attributes.add("volt");
        attributes.add("dodge");
        attributes.add("dodgedefence");
        attributes.add("ignorephysicdefence");
        attributes.add("ignoremagicdefence");
        attributes.add("stunresist");
        attributes.add("petrifactionresist");
        attributes.add("transmutationresist");
        attributes.add("slowresist");
        attributes.add("curseresist");
        attributes.add("attackrate");
        attributes.add("freezeresist");
        attributes.add("mb");
        attributes.add("mbdk");
        attributes.add("yx");
        attributes.add("yxdk");
        attributes.add("bh");
        attributes.add("bhdk");
        attributes.add("xx");
        attributes.add("xxdk");
        attributes.add("dunshudian");
        attributes.add("criticalb");
        attributes.add("criticalc");
        attributes.add("pojia");
        attributes.add("qiege");
        attributes.add("jiangu");
        attributes.add("shishen");
        attributes.add("shenyou");
        attributes.add("damage");
        attributes.add("defence");
        attributes.add("perdamage");
        attributes.add("redamage");
        attributes.add("reph");
    }
}