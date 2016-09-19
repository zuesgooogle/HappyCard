package com.s4game.server.stage.room;

public class RoomStageFactory {

    public static RoomStage create(String stageId, String mapId) {
        return new RoomStage(stageId, mapId);
    }
}
