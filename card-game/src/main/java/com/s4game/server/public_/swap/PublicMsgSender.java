package com.s4game.server.public_.swap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.message.Message;
import com.s4game.core.message.Message.DestType;
import com.s4game.core.message.Message.FromType;
import com.s4game.server.message.IMsgDispatcher;
import com.s4game.server.message.manager.SwapManager;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月9日 下午5:46:50
 * 
 */
@Component
public class PublicMsgSender {

    @Resource
    private IMsgDispatcher publicDispatcher;

    @Resource
    private SwapManager swapManager;

    public void send2OneBySessionId(String command, String userId, String sessionId, Object data) {
        //Object[] message = new Object[] { command, data, DestType.CLIENT.getValue(), FromType.BUS.getValue(), 1, sessionId, null, userId, 0, null };

        Message message = new Message(command, data, FromType.BUS, DestType.CLIENT, userId, sessionId);
        message.setRoute(1);
        
        swapManager.swap(message);
    }

//    public void send2One(String command, String userId, Object data) {
//    	Message message = new Message(command, data, FromType.BUS, DestType.CLIENT, userId, "");
//    	message.setRoute(1);
//    	
//    	swapManager.swap(message);
//    }

    public void send2PublicInner(String command, String roleId, Object data) {
        //Object[] message = new Object[] { command, data, DestType.STAGE_CONTROL.getValue(), FromType.BUS.getValue(), 1, null, null, userId, 0, null };
        
        Message message = new Message(command, data, FromType.BUS, DestType.STAGE_CONTROL, roleId);
        
        publicDispatcher.in(message);
    }
}
