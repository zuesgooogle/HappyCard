package com.s4game.server.gamerule.goods.configure.export.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.s4game.server.configure.export.IConfigureExportService;
import com.s4game.server.configure.parser.impl.AbsClasspathConfigureParser;
import com.s4game.server.gamerule.goods.configure.export.IGoodsConfigService;
import com.s4game.server.utils.ConvertObjectUtil;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月28日 下午3:04:45
 *
 */
@Component
public class GoodsConfigServiceImpl extends AbsClasspathConfigureParser implements IGoodsConfigService {

    @Resource
    private IConfigureExportService configureExportService;

    @Override
    protected void configureDataResolve(byte[] bytes) {
        String data = new String(bytes);
        
        JSONArray array = JSON.parseArray(data);
        for (Object object : array) {
            JSONObject json = (JSONObject)object;
   
            GoodsConfig config = createConfig(json);
            configureExportService.add( config );
        }
    }
    
    private GoodsConfig createConfig(JSONObject json) {
        GoodsConfig config = new GoodsConfig();
        config.setId(json.getString("id"));
        config.setName(json.getString("name"));
        config.setItemLevel(json.getIntValue("itemlevel"));
        config.setRareLevel(json.getIntValue("rarelevel"));
        config.setLevelReq(json.getIntValue("levelreq"));
        config.setCategory(json.getIntValue("category"));
        config.setMaxStack(json.getIntValue("maxstack"));
        config.setUseable(ConvertObjectUtil.object2boolean(json.get("useable")));
        config.setBuffId(json.getString("buffid"));
        config.setJob(json.getString("job"));
        config.setCostMoney(json.getString("costmoney"));
        config.setCd(json.getString("cd"));
        config.setOrder(ConvertObjectUtil.object2int(json.get("order")));
        
        return config;
    } 
    
    @Override
    public GoodsConfig loadById(String goodsId) {
        return configureExportService.get(GoodsConfig.class, goodsId);
    }

    @Override
    protected String getConfigureName() {
        return "goods.dat";
    }

}
