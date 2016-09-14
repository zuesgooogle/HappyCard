package com.s4game.server.bus.bag.moduleinit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.core.event.IEventHandler;
import com.s4game.server.bus.bag.BagModuleInfo;
import com.s4game.server.bus.bag.command.BagCommands;
import com.s4game.server.bus.bag.dao.cache.RoleBagCacheModelLoader;
import com.s4game.server.bus.bag.event.subscribe.BagRoleCreateEventHandler;
import com.s4game.server.bus.share.moduleinit.BusModuleInit;
import com.s4game.server.share.moduleinit.CommandRegister;
import com.s4game.server.share.moduleinit.Group;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月12日 下午4:43:46
 *
 */
@Component
public class BagModuleInit extends BusModuleInit {

    @Resource
    private RoleBagCacheModelLoader roleBagCacheModelLoader;
    
    @Resource
    private BagRoleCreateEventHandler bagRoleCreateEventHandler;
    
    @Override
    protected InCmd getInCmd() {
        String[] cmds = new String[] {BagCommands.GET_BAG_GOODS};
        return new InCmd(BagModuleInfo.MODULE_NAME, Group.BUS.name, cmds);
    }
    
    @Override
    protected ModuleInfo getModuleInfo() {
        return new ModuleInfo(BagModuleInfo.MODULE_NAME, BagModuleInfo.MODULE_NAME_ABB);
    }

    @Override
    protected IEntityCacheModelLoader[] getEntityCacheModelLoaders() {
        return new IEntityCacheModelLoader[] { this.roleBagCacheModelLoader };
    }
    
    protected IEventHandler[] getEventHandlers() {
        return new IEventHandler[] { this.bagRoleCreateEventHandler };
    }
}
