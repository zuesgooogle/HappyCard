package com.s4game.server.stage.configure.export;

import com.s4game.server.stage.configure.export.impl.BuffConfig;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月27日 下午5:36:44
 *
 */

public interface IBuffConfigService {

    public BuffConfig loadById(String id);
    
    public BuffConfig loadByLevel(String id, int level);

    
}
