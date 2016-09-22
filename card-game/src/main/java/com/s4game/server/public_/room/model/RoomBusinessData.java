package com.s4game.server.public_.room.model;

import java.util.ArrayList;
import java.util.List;

public class RoomBusinessData {

    private long roomId;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 房间状态
     */
    private int status;

    /**
     * 总回合数
     */
    private int maxRound;

    /**
     * 当前回合 <= round
     */
    private int curRound;
    
    /**
     * 是否连中
     */
    private boolean serial;

    /**
     * 是否强制胡牌
     */
    private boolean win;

    /**
     * 房间成员数据
     * <pre>根据加入房间顺序</pre>
     */
    private List<RoomMemberData> members = new ArrayList<>();

    /**
     * 初始化一副牌数据
     */
    private List<CardData> initCards;
    
    /**
     * 剩余牌列表
     */
    private List<CardData> remainCards;
    
    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMaxRound() {
        return maxRound;
    }

    public void setMaxRound(int maxRound) {
        this.maxRound = maxRound;
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

    public List<RoomMemberData> getMembers() {
        return members;
    }

    public void setMembers(List<RoomMemberData> members) {
        this.members = members;
    }

    public RoomMemberData findMemberData(String roleId) {
        for (RoomMemberData md : members) {
            if (md.getRoleId().equals(roleId)) {
                return md;
            }
        }
        return null;
    }
    
    public List<CardData> getInitCards() {
        return initCards;
    }

    public void setInitCards(List<CardData> initCards) {
        this.initCards = initCards;
    }

    public List<CardData> getRemainCards() {
        return remainCards;
    }

    public void setRemainCards(List<CardData> remainCards) {
        this.remainCards = remainCards;
    }
    
}
