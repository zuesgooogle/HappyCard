package com.s4game.server.bus.vip.configure;

import com.s4game.server.bus.vip.configure.impl.VipConfig;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月3日 上午11:55:22
 *
 */

public interface IVipConfigureService {

    public VipConfig loadByLevel(int level);
    
}
