package com.s4game.server.stage.model.stage.aoi;

import org.springframework.stereotype.Component;

import com.s4game.server.bus.map.configure.impl.MapConfig;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月17日 下午4:31:08
 *
 */
@Component
public class AoiStageFactory {

    public AoiStage create(String stageId, MapConfig mapConfig) {
        AoiStage stage = createStage(stageId, mapConfig);
        
        
        return stage;
    }
    
    private AoiStage createStage(String stageId, MapConfig mapConfig) {
        return new AoiStage(stageId, mapConfig.getId());
    }
    
}
