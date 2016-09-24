package com.s4game.server.public_.card.service;

import com.s4game.server.stage.room.RoomStage;

public interface ICardService {

    /**
     * 发牌
     * @param stage
     */
    void deal(RoomStage stage);
    
    /**
     * 出牌
     * 
     * @param roleId
     * @param cardId
     */
    void play(String roleId, String cardId);
}
