package com.s4game.server.configure.moduleinit;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventService;
import com.s4game.server.share.export.IEntityCacheLoaderExportService;
import com.s4game.server.share.moduleinit.ModuleInit;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月3日 上午9:41:19
 *
 */
@Component
public class ConfigureModuleInit extends ModuleInit {

    @Override
    public IEventService getEventService() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IEntityCacheLoaderExportService getEntityCacheLoaderExportService() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected InCmd getInCmd() {
        // TODO Auto-generated method stub
        return null;
    }

}
