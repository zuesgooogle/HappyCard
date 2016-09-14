package com.s4game.server.configure.parser;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.s4game.server.configure.export.impl.ConfigureContext;
import com.s4game.server.configure.loader.DirType;
import com.s4game.server.configure.loader.IConfigureResourceLoader;
import com.s4game.server.share.log.Log;

public abstract class AbsConfigureParser {

    protected Logger LOG = LogManager.getLogger(Log.CONFIG_LOGGER);
    
    protected String sign;
    
    public String _getSign() {
        return this.sign;
    }

    @PostConstruct
    public void init() {
        try {
            Object[] signInfo = getLoader().loadSign(this.getConfigureName(), DirType.GLOBAL);
            if (signInfo == null) {
                return;
            }
            
            this.sign = (String)signInfo[0];
            byte[] bytes = this.getLoader().load( (String)signInfo[1] );
            if (bytes == null) {
                LOG.error("cannot find configureName {}", getConfigureName() );
                return;
            }

            this.configureDataResolve(bytes);
        }
        catch (Exception e) {
            LOG.error("parser configureName: {} error. message: {}", getConfigureName(), e);
        }
    }

    protected abstract void configureDataResolve(byte[] bytes);

    protected abstract String getConfigureName();

    protected abstract IConfigureResourceLoader getLoader();

    protected String getIdentity() {
        return ConfigureContext.CONFIGURE_IDENTITY;
    }

}

