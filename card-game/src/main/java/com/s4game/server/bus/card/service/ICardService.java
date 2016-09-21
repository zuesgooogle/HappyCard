package com.s4game.server.bus.card.service;

import com.s4game.server.stage.model.core.stage.IStage;

public interface ICardService {

    /**
     * 发牌
     * @param stage
     */
    void deal(IStage stage);
    
}
