package com.s4game.server.stage.room;

import com.s4game.server.stage.model.core.stage.AbsStage;
import com.s4game.server.stage.model.core.stage.IStageElement;

public class RoomStage extends AbsStage {

    private String id;
    
    private String mapId;
    
    public RoomStage(String stageId, String mapId) {
        this.id = stageId;
        this.mapId = mapId;
    }
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getMapId() {
        return mapId;
    }

    @Override
    public boolean isCopy() {
        return true;
    }

    @Override
    protected void addHandler(IStageElement stageElement) {
        stageElement.enterStageHandle(this);
    }

    @Override
    protected void deleteHandler(IStageElement stageElement) {
        stageElement.leaveStageHandle(this);
    }

}
