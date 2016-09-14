package com.s4game.server.bus.client.io.action;

import javax.annotation.Resource;

import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.bus.client.io.command.ClientIoCommands;
import com.s4game.server.bus.client.io.service.IIoService;

@ActionWorker
public class BusInitAction {
    
    @Resource
    private IIoService ioService;

    @ActionMapping(mapping = ClientIoCommands.ROLE_IN)
    public void roleIn(Message message) {
        String str = (String) message.getData();
        this.ioService.roleIn(message.getRoleId(), str);
    }
}
