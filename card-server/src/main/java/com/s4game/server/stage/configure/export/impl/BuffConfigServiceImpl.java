package com.s4game.server.stage.configure.export.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s4game.server.configure.export.IConfigureExportService;
import com.s4game.server.configure.parser.impl.AbsClasspathConfigureParser;
import com.s4game.server.stage.configure.export.IBuffConfigService;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月27日 下午5:38:41
 *
 */
@Component
public class BuffConfigServiceImpl extends AbsClasspathConfigureParser implements IBuffConfigService {

    @Autowired
    private IConfigureExportService configureExportService;
    
    private String configureName = "buff.dat";

    @Override
    protected void configureDataResolve(byte[] bytes) {

    }

    
    @Override
    public BuffConfig loadById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BuffConfig loadByLevel(String id, int level) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getConfigureName() {
        return configureName;
    }

}
