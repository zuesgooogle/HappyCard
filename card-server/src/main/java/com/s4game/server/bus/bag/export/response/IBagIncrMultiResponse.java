package com.s4game.server.bus.bag.export.response;

import java.util.Map;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public interface IBagIncrMultiResponse {
    
    public Map<String, Integer> getGoodsMap();

    public Object[] getAddInfos();

    public Object[] getUpdateInfos();

    public Object[] getAllInfos();

    public boolean isSuccess();
}
