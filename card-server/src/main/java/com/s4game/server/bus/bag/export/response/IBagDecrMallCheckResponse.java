package com.s4game.server.bus.bag.export.response;

import java.util.Map;

import com.s4game.server.gamerule.money.MoneyType;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public interface IBagDecrMallCheckResponse {
    
    public Map<String, Integer> getDecrMallGoodsMap();

    public Map<String, Integer> getMallBuyGoodsMap();

    public Map<MoneyType, Integer> getMallDecrMoney();

    public Integer getError();

    public boolean isSuccess();
}
