package com.s4game.server.configure.loader.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.s4game.server.configure.loader.DirType;
import com.s4game.server.configure.loader.IConfigureResourceLoader;
import com.s4game.server.share.log.Log;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年7月3日 上午9:45:45
 * 
 */
@Component
public class ClasspathConfigureLoader implements IConfigureResourceLoader {

    private Logger LOG = LogManager.getLogger(Log.CONFIG_LOGGER);

    public static final String GLOBAL_FILE_DIR = "config/game/sys_config/";

    public static final String MAP_RESOURCE_FILE_DIR = "config/game/map_config/";

    @Override
    public byte[] load(String configureName) {
        
        BufferedInputStream bufferedInputStream = null;
        try {
            int n;
            File file = new File(ClassLoader.getSystemResource("").getFile() + configureName);

            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((n = bufferedInputStream.read()) != -1) {
                byteArrayOutputStream.write(n);
            }
            
            return byteArrayOutputStream.toByteArray();
            
        } catch (Exception e) {
            LOG.error("load config error. message: {}", configureName, e);
            try {
                if (null != bufferedInputStream) {
                    bufferedInputStream.close();
                }
            } catch (IOException e1) {
            }
        } finally {
            if (null != bufferedInputStream) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    LOG.error("load config error. message: {}", configureName, e);
                }
            }
        }
        
        return null;
    }

    @Override
    public Object[] loadSign(String configureName, DirType type) {
        String dir = GLOBAL_FILE_DIR;
        if (type.equals(DirType.MAPRESOURCE)) {
            dir = MAP_RESOURCE_FILE_DIR;
        }
        String sign = dir + configureName;
        return new Object[]{null, sign};
    }

}
