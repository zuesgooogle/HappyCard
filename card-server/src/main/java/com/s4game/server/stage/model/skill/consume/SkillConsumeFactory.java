package com.s4game.server.stage.model.skill.consume;

import java.util.HashMap;
import java.util.Map;

import com.s4game.server.stage.model.core.fight.ISkillConsume;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月4日 下午5:21:58
 *
 */

public class SkillConsumeFactory {

    private static Map<Integer, ISkillConsume> consumeMap = new HashMap();
    
    public static ISkillConsume getSkillConsume(int type) {
        ISkillConsume skillConsume = consumeMap.get(type);
        if( null == skillConsume ) {
            skillConsume = consumeMap.get(SkillConsumes.CONSUME_TYPE_EMPTY);
        }
        return skillConsume;
    }
    
    static {
        consumeMap.put(SkillConsumes.CONSUME_TYPE_EMPTY, new ConsumeEmpty());
        
        consumeMap.put(SkillConsumes.CONSUME_TYPE_MP, new ConsumeMp());
    }
    
}
