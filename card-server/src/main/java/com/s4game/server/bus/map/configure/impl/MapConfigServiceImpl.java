package com.s4game.server.bus.map.configure.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.s4game.server.bus.map.configure.IMapConfigService;
import com.s4game.server.configure.export.IConfigureExportService;
import com.s4game.server.configure.parser.impl.AbsClasspathConfigureParser;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月17日 上午10:39:21
 *
 */
@Component
public class MapConfigServiceImpl extends AbsClasspathConfigureParser implements IMapConfigService {

    private static final String CONFIGURE_NAME = "map.dat";
    
    @Resource
    private IConfigureExportService configureExportService;
    
    @Override
    protected void configureDataResolve(byte[] bytes) {
        String data = new String(bytes);
        
        JSONArray array = JSON.parseArray(data);
        for (Object object : array) {
            JSONObject json = (JSONObject)object;
            
            MapConfig config = createConfig(json);
            configureExportService.add(config);
        }
    }
        
    private MapConfig createConfig(JSONObject json) {
        MapConfig config = new MapConfig();
        
        config.setId( json.getString("id") );
        config.setName( json.getString("name") );
        
        return config;
    }
    
    @Override
    public MapConfig load(String id) {
        return configureExportService.get(MapConfig.class, id);
    }

    @Override
    public List<MapConfig> loadAll() {
        return configureExportService.get(MapConfig.class);
    }

    @Override
    protected String getConfigureName() {
        return CONFIGURE_NAME;
    }

}
