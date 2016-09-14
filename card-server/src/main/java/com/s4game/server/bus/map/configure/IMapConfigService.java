package com.s4game.server.bus.map.configure;

import java.util.List;

import com.s4game.server.bus.map.configure.impl.MapConfig;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月17日 上午10:26:13
 * 
 */

public interface IMapConfigService {

    public MapConfig load(String id);

    public List<MapConfig> loadAll();

}
