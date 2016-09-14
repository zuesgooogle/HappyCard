package com.s4game.server.configure.parser.impl;

import javax.annotation.Resource;

import com.s4game.server.configure.loader.IConfigureResourceLoader;
import com.s4game.server.configure.loader.impl.RemoteConfigureLoader;
import com.s4game.server.configure.parser.AbsRefreshConfigureParser;
import com.s4game.server.configure.parser.IRefreshConfigureParser;

public abstract class AbsRemoteRefreshConfigureParser extends AbsRefreshConfigureParser implements IRefreshConfigureParser {
    
    @Resource
    private RemoteConfigureLoader configureLoader;

    @Override
    protected IConfigureResourceLoader getLoader() {
        return this.configureLoader;
    }

    @Override
    public String getConfigName() {
        return this.getConfigureName();
    }
}
