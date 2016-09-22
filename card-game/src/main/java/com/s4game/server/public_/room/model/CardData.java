package com.s4game.server.public_.room.model;

public class CardData {

    private String id;

    private int value;

    public CardData() {

    }

    public CardData(String id, int value) {
        this.id = id;
        this.value = value;
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

    @Override
    public String toString() {
        return "Card [id=" + id + ", value=" + value + "]";
    }

}
