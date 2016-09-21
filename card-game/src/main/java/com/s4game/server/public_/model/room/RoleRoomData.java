package com.s4game.server.public_.model.room;

import java.util.ArrayList;
import java.util.List;

import com.s4game.server.public_.model.card.CardData;

public class RoleRoomData {

    private int roomId;

    /**
     * 玩家手牌
     */
    private List<CardData> cards = new ArrayList<>();

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<CardData> getCards() {
        return cards;
    }

    public void setCards(List<CardData> cards) {
        this.cards = cards;
    }

    public void addCard(CardData card) {
        this.cards.add(card);
    }
}
