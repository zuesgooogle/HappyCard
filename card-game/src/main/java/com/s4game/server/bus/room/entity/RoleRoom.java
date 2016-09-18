package com.s4game.server.bus.room.entity;

import java.sql.Timestamp;

import com.s4game.core.data.AbsVersion;
import com.s4game.core.data.IEntity;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月17日 下午12:12:02
 *
 */
public class RoleRoom extends AbsVersion implements IEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 5764691377281646396L;

    private String userRoleId;

    private int number;

    private int round;

    private boolean serial;

    private boolean win;

    private Timestamp logUpdateTime;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
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

    public Timestamp getLogUpdateTime() {
        return logUpdateTime;
    }

    public void setLogUpdateTime(Timestamp logUpdateTime) {
        this.logUpdateTime = logUpdateTime;
    }

    @Override
    public String getPirmaryKeyName() {
        return "userRoleId";
    }

    @Override
    public Object getPrimaryKeyValue() {
        return getUserRoleId();
    }

    @Override
    public IEntity copy() {
        RoleRoom room = new RoleRoom();
        room.setUserRoleId(getUserRoleId());
        room.setNumber(getNumber());
        room.setRound(getRound());
        room.setSerial(isSerial());
        room.setWin(isWin());

        return room;
    }

}
