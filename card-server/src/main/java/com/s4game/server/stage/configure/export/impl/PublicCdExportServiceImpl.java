package com.s4game.server.stage.configure.export.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.s4game.server.bus.vip.configure.impl.VipConfig;
import com.s4game.server.configure.export.IConfigureExportService;
import com.s4game.server.configure.parser.impl.AbsClasspathConfigureParser;
import com.s4game.server.stage.configure.export.IPublicCdExportService;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月4日 下午3:34:12
 *
 */
@Component
public class PublicCdExportServiceImpl extends AbsClasspathConfigureParser implements IPublicCdExportService {

    public Logger LOG = LoggerFactory.getLogger(getClass());
    
    @Resource
    private IConfigureExportService configureExportService;

    @Override
    protected void configureDataResolve(byte[] bytes) {
        String data = new String(bytes);
        
        JSONArray array = JSON.parseArray(data);
        for (Object object : array) {
            JSONObject json = (JSONObject)object;
            
            PublicCdConfig config = createConfig(json);
            configureExportService.add( config );
        }
    }

    private PublicCdConfig createConfig(JSONObject json) {
        PublicCdConfig config = new PublicCdConfig();
        config.setId(json.getString("id"));
        config.setTime(json.getIntValue("time"));
        
        return config;
    }
    
    @Override
    public PublicCdConfig loadById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getConfigureName() {
        return "cd.dat";
    }

}
