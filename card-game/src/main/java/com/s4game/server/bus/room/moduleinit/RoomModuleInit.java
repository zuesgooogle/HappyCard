package com.s4game.server.bus.room.moduleinit;

import static com.s4game.server.bus.room.command.RoomCommands.CREATE_ROOM;
import static com.s4game.server.bus.room.command.RoomCommands.DISMISS_ROOM;
import static com.s4game.server.bus.room.command.RoomCommands.JOIN_ROOM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.core.event.IEventHandler;
import com.s4game.server.bus.room.RoomModuleInfo;
import com.s4game.server.bus.room.dao.cache.RoleRoomCacheModelLoader;
import com.s4game.server.bus.room.event.subscribe.RoleLoginEventHandler;
import com.s4game.server.bus.share.moduleinit.BusModuleInit;
import com.s4game.server.share.moduleinit.Group;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午12:08:58 
*
*/
@Component
public class RoomModuleInit extends BusModuleInit {

    @Autowired
    private RoleRoomCacheModelLoader roleRoomCacheModelLoader;
    
    @Autowired
    private RoleLoginEventHandler roleLoginEventHandler;
    
    @Override
    protected InCmd getInCmd() {
        return new InCmd(RoomModuleInfo.MODULE_NAME, Group.BUS.name, new String[] { CREATE_ROOM, JOIN_ROOM, DISMISS_ROOM });
    }

    protected ModuleInfo getModuleInfo() {
        return new ModuleInfo(RoomModuleInfo.MODULE_NAME, RoomModuleInfo.MODULE_NAME_ALIAS);
    }
    
    protected IEntityCacheModelLoader[] getEntityCacheModelLoaders() {
        return new IEntityCacheModelLoader[] { roleRoomCacheModelLoader };
    }

    protected IEventHandler[] getEventHandlers() {
        return new IEventHandler[] { roleLoginEventHandler };
    }
    
}
