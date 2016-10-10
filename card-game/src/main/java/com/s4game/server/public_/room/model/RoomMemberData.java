package com.s4game.server.public_.room.model;

import java.util.ArrayList;
import java.util.List;

import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.public_.card.model.card.Card;

public class RoomMemberData {

    private String roleId;

    private String name;

    private String face;

    /**
     * 是否已准备（在线）
     */
    private boolean ready;

    /**
     * 庄家
     */
    private boolean dealer;

    /**
     * 玩家进的牌
     */
    private List<Card> inCards = new ArrayList<>();

    /**
     * 玩家打出的牌
     */
    private List<Card> outCards = new ArrayList<>();

    /**
     * 玩家手牌
     */
    private List<Card> handCard = new ArrayList<>();

    public RoomMemberData(RoleWrapper role) {
        this.roleId = role.getId();
        this.name = role.getName();
        this.face = role.getFace();
    }

    public String getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public String getFace() {
        return face;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isDealer() {
        return dealer;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

    public List<Card> getInCards() {
        return inCards;
    }

    public void setInCards(List<Card> inCards) {
        this.inCards = inCards;
    }

    public List<Card> getOutCards() {
        return outCards;
    }

    public void setOutCards(List<Card> outCards) {
        this.outCards = outCards;
    }

    public List<Card> getHandCard() {
        return handCard;
    }

    public void setHandCard(List<Card> handCard) {
        this.handCard = handCard;
    }

}
