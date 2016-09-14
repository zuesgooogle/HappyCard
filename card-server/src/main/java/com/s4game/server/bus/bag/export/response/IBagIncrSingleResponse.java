package com.s4game.server.bus.bag.export.response;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public interface IBagIncrSingleResponse {
    
    public String getGoodsId();

    public int getGoodsCount();

    public Object[] getAddInfos();

    public Object[] getUpdateInfos();

    public Object[] getAllInfos();

    public boolean isSuccess();

    public String getGuId();
}
