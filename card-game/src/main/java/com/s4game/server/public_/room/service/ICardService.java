package com.s4game.server.public_.room.service;

import com.s4game.server.stage.room.RoomStage;

public interface ICardService {

    /**
     * 发牌
     * @param stage
     */
    void deal(RoomStage stage);
    
}
