package com.s4game.server.bus.init.action;

import javax.annotation.Resource;

import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.bus.init.command.InitCommands;
import com.s4game.server.bus.init.service.InitService;

@ActionWorker
public class BusOutAction {
    
    @Resource
    private InitService ioService;

    @ActionMapping(mapping = InitCommands.ROLE_OUT)
    public void roleOut(Message message) {
        this.ioService.roleOut(message.getRoleId());
    }
}
