package com.s4game.server.public_.room.model;

import java.util.List;

import com.s4game.server.bus.role.export.RoleWrapper;

public class RoomMemberData {
    
    private RoleWrapper role;
    
    /**
     * 是否已准备（在线）
     */
    private boolean ready;
    
    /**
     * 玩家进的牌
     */
    private List<CardData> inCards;
    
    /**
     * 玩家打出的牌
     */
    private List<CardData> outCards;
    
    /**
     * 玩家手牌
     */
    private List<CardData> handCard;

    public RoomMemberData(RoleWrapper role) {
        this.role = role;
    }
    
    public String getRoleId() {
        return role.getId();
    }

    public String getName() {
        return role.getName();
    }

    public String getFace() {
        return role.getFace();
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public List<CardData> getInCards() {
        return inCards;
    }

    public void setInCards(List<CardData> inCards) {
        this.inCards = inCards;
    }

    public List<CardData> getOutCards() {
        return outCards;
    }

    public void setOutCards(List<CardData> outCards) {
        this.outCards = outCards;
    }

    public List<CardData> getHandCard() {
        return handCard;
    }

    public void setHandCard(List<CardData> handCard) {
        this.handCard = handCard;
    }
    
}
