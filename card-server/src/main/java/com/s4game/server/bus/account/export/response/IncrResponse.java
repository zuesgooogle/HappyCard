package com.s4game.server.bus.account.export.response;

import com.s4game.server.gamerule.money.MoneyType;

public class IncrResponse {

    private MoneyType moneyType;
    private boolean success;
    private long incr;

    public IncrResponse(boolean success, MoneyType moneyType, long incr) {
        this.success = success;
        this.moneyType = moneyType;
        this.incr = incr;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public long getIncr() {
        return this.incr;
    }

    public MoneyType getIncrMoneyType() {
        return this.moneyType;
    }
}