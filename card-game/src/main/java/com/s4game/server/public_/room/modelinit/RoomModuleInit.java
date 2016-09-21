package com.s4game.server.public_.room.modelinit;

import static com.s4game.server.public_.room.command.RoomCommands.CREATE_ROOM;
import static com.s4game.server.public_.room.command.RoomCommands.DISMISS_ROOM;
import static com.s4game.server.public_.room.command.RoomCommands.JOIN_ROOM;

import org.springframework.stereotype.Component;

import com.s4game.server.public_.room.RoomModuleInfo;
import com.s4game.server.public_.share.PublicModuleInit;
import com.s4game.server.share.event.EventHandleCommands;
import com.s4game.server.share.moduleinit.Group;

@Component
public class RoomModuleInit extends PublicModuleInit {

    @Override
    public EventHandleCommands getEventHandleCommands() {
        return null;
    }

    @Override
    protected InCmd getInCmd() {
        return new InCmd(RoomModuleInfo.MODULE_NAME, Group.PUBLIC.name, new String[] {CREATE_ROOM, JOIN_ROOM, DISMISS_ROOM});
    }

}
