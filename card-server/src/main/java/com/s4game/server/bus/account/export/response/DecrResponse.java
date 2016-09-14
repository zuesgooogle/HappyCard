package com.s4game.server.bus.account.export.response;

import com.s4game.server.gamerule.money.MoneyType;

public class DecrResponse {

    private MoneyType moneyType;
    private boolean success;
    private long decr;

    public DecrResponse(boolean success, MoneyType moneyType, long decr) {
        this.success = success;
        this.moneyType = moneyType;
        this.decr = decr;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public long getDecr() {
        return this.decr;
    }

    public MoneyType getDecrMoneyType() {
        return this.moneyType;
    }
}