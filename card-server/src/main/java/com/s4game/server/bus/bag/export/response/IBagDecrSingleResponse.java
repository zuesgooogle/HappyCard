package com.s4game.server.bus.bag.export.response;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public interface IBagDecrSingleResponse {

    public String getDecrGoodsId();

    public int getDecrGoodsCount();

    public boolean isDecrGoodsBind();

    public boolean isSuccess();
    
}
