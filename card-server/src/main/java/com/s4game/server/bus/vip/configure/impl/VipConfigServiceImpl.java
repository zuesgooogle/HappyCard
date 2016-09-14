package com.s4game.server.bus.vip.configure.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.s4game.server.bus.vip.configure.IVipConfigureService;
import com.s4game.server.configure.export.IConfigureExportService;
import com.s4game.server.configure.parser.impl.AbsClasspathConfigureParser;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月3日 上午11:58:32
 *
 */
@Component
public class VipConfigServiceImpl extends AbsClasspathConfigureParser implements IVipConfigureService {

    @Resource
    private IConfigureExportService configureExportService;
    
    @Override
    protected void configureDataResolve(byte[] bytes) {
        String data = new String(bytes);
        
        JSONArray array = JSON.parseArray(data);
        for (Object object : array) {
            JSONObject json = (JSONObject)object;
            
            VipConfig config = createConfig(json);
            configureExportService.add( config );
        }
    }
    
    private VipConfig createConfig(JSONObject json) {
        VipConfig config = new VipConfig();
        config.setLevel(json.getInteger("level"));
        config.setName(json.getString("describe"));
        
        return config;
    }

    @Override
    protected String getConfigureName() {
        return "vip.dat";
    }

    @Override
    public VipConfig loadByLevel(int level) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
