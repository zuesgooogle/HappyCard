package com.s4game.server.configure.loader;


public interface IConfigureResourceLoader {
    
    public byte[] load(String configureName);

    public Object[] loadSign(String configureName, DirType type);
    
}

