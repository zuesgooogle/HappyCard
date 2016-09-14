package com.s4game.server.configure.export.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.manager.CacheManager;

@Component
public class ConfigureContext {
    
    @Resource
    private CacheManager configureCacheManager;
    
    public static final String CONFIGURE_IDENTITY = "!@#$configure";

    public void init() {
        this.configureCacheManager.activateRoleCache(CONFIGURE_IDENTITY);
    }

    public CacheManager getCacheManager() {
        return this.configureCacheManager;
    }
}

