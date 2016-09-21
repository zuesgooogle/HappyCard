package com.s4game.server.public_.model.room;

import java.util.ArrayList;
import java.util.List;

import com.s4game.server.public_.model.card.CardData;

public class RoomData {

    private int roomId;

    private String roomLeaderId;

    private String[] roomMembers;
    
    private int round;
    
    private int curRound;
    
    private boolean serial;
    
    private boolean win;
    
    private int status;
    
    /**
     * 中庄角色ID
     */
    private String bankerId;
    
    /**
     * 房间剩余牌
     */
    private List<CardData> cards = new ArrayList<>();

    public RoomData() {

    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomLeaderId() {
        return roomLeaderId;
    }

    public void setRoomLeaderId(String roomLeaderId) {
        this.roomLeaderId = roomLeaderId;
    }

    public String[] getRoomMembers() {
        return roomMembers;
    }

    public void setRoomMembers(String[] roomMembers) {
        this.roomMembers = roomMembers;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getCurRound() {
        return curRound;
    }

    public void setCurRound(int curRound) {
        this.curRound = curRound;
    }

    public boolean isSerial() {
        return serial;
    }

    public void setSerial(boolean serial) {
        this.serial = serial;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBankerId() {
        return bankerId;
    }

    public void setBankerId(String bankerId) {
        this.bankerId = bankerId;
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
