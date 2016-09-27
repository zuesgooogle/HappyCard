package com.s4game.server.public_.room.model;

public class CardData {

    private String id;

    private int value;

    private boolean big;

    public CardData() {

    }

    public CardData(String id, int value) {
        this(id, value, false);
    }
    
    public CardData(String id, int value, boolean big) {
        this.id = id;
        this.value = value;
        this.big = big;
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

    public boolean isBig() {
        return big;
    }

    public void setBig(boolean big) {
        this.big = big;
    }

    public String getKey() {
        return value + "-" + big;
    }
    
    public boolean isSame(CardData card) {
        return value == card.getValue() && big == card.isBig();
    }
    
    @Override
    public String toString() {
        return "CardData [id=" + id + ", value=" + value + ", big=" + big + "]";
    }

}
