package com.s4game.server.configure.parser.impl;

import javax.annotation.Resource;

import com.s4game.server.configure.loader.IConfigureResourceLoader;
import com.s4game.server.configure.loader.impl.ClasspathConfigureLoader;
import com.s4game.server.configure.parser.AbsConfigureParser;

public abstract class AbsClasspathConfigureParser extends AbsConfigureParser {
    
    @Resource
    private ClasspathConfigureLoader configureLoader;

    @Override
    protected IConfigureResourceLoader getLoader() {
        return this.configureLoader;
    }
}
