package com.s4game.server.stage.model.element.impl.state;

import java.util.HashMap;
import java.util.Map;

import com.s4game.server.stage.model.core.element.IState;
import com.s4game.server.stage.model.core.element.impl.state.StateType;

public class BuffStateFactory {
    
    private static Map<Integer, StateType> stateTypes = new HashMap();
    
    private static Map<Integer, Class<? extends IState>> states;

    public static IState create(Integer type) {
        Class clazz = states.get(type);
        if (null != clazz) {
            try {
                return (IState) clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static StateType getType(Integer paramInteger) {
        return (StateType) stateTypes.get(paramInteger);
    }

    static {
        stateTypes.put(Integer.valueOf(21), StateType.KONGJU);
        stateTypes.put(Integer.valueOf(22), StateType.HUNMI);
        stateTypes.put(Integer.valueOf(23), StateType.BINGDONG);
        stateTypes.put(Integer.valueOf(24), StateType.SHIHUA);
        stateTypes.put(Integer.valueOf(25), StateType.MABI);
        stateTypes.put(Integer.valueOf(26), StateType.JINGU);
        stateTypes.put(Integer.valueOf(27), StateType.JITUI);
        stateTypes.put(Integer.valueOf(28), StateType.WUDI_NOMOVE);
        stateTypes.put(Integer.valueOf(29), StateType.BIAN_XING);
        stateTypes.put(Integer.valueOf(31), StateType.CHENMO);
        stateTypes.put(Integer.valueOf(32), StateType.WUDI);
        stateTypes.put(Integer.valueOf(33), StateType.SATAGEPRETECT);
        stateTypes.put(Integer.valueOf(34), StateType.PKFREE);
        stateTypes.put(Integer.valueOf(36), StateType.LINGJIEHUDUN);
        
        states = new HashMap();
        
//        states.put(Integer.valueOf(21), KongJuState.class);
//        states.put(Integer.valueOf(22), HunMi.class);
//        states.put(Integer.valueOf(23), BingDong.class);
//        states.put(Integer.valueOf(24), ShiHua.class);
//        states.put(Integer.valueOf(25), MaBi.class);
//        states.put(Integer.valueOf(26), JinGu.class);
//        states.put(Integer.valueOf(27), JiTui.class);
//        states.put(Integer.valueOf(28), WuDiNoMove.class);
//        states.put(Integer.valueOf(29), BianXing.class);
//        states.put(Integer.valueOf(31), ChenMo.class);
//        states.put(Integer.valueOf(32), WuDi.class);
//        states.put(Integer.valueOf(33), StageProtect.class);
//        states.put(Integer.valueOf(34), PKFreeState.class);
//        states.put(Integer.valueOf(35), NightlyProtectState.class);
//        states.put(Integer.valueOf(36), LingJieHuDunState.class);
    }
}