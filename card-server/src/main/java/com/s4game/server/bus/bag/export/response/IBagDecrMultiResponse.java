package com.s4game.server.bus.bag.export.response;

import java.util.List;
import java.util.Map;

import com.s4game.server.gamerule.goods.creator.ItemModel;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public interface IBagDecrMultiResponse {

    public Map<String, Integer> getDecrGoodsMap();

    public boolean isSuccess();

    public Integer getError();

    public boolean isBind();

    public List<ItemModel> getDecrItemModels();
}
