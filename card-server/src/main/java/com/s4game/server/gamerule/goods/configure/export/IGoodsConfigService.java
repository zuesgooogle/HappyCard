package com.s4game.server.gamerule.goods.configure.export;

import com.s4game.server.gamerule.goods.configure.export.impl.GoodsConfig;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月28日 下午2:53:51
 *
 */

public interface IGoodsConfigService {

    public GoodsConfig loadById(String goodsId);
    
}
