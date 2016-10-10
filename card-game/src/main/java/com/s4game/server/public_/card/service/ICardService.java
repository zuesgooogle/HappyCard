package com.s4game.server.public_.card.service;

import java.util.List;

import com.s4game.server.public_.card.model.card.Card;
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
    
    /**
     * 初始化一副牌
     * 
     * @param stageId
     * @return
     */
    List<Card> initCards(String stageId);
}
