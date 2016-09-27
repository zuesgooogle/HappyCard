package com.s4game.server.public_.room.model;

import com.s4game.server.public_.card.model.CardType;

public class CardData {

    private String id;

    private int value;

    private int type;

    public CardData() {

    }

    public CardData(String id, int value) {
        this(id, value, CardType.SMALL);
    }

    public CardData(String id, int value, int type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKey() {
        return value + "-" + type;
    }

    public boolean isSame(CardData card) {
        return value == card.getValue() && type == card.getType();
    }

    @Override
    public String toString() {
        return "CardData [value=" + value + ", type=" + type + "]";
    }

}
