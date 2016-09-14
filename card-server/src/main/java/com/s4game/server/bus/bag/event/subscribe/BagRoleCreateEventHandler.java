package com.s4game.server.bus.bag.event.subscribe;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.bus.bag.service.IBagService;
import com.s4game.server.share.event.EventConstants;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年9月6日 下午5:00:57
 *
 */
@Component
public class BagRoleCreateEventHandler implements IEventHandler {

    @Resource
    private IBagService bagService;
    
    @Override
    public void handle(Object source, Object data) {
        Object[] array = (Object[]) data;
        
        String roleId = (String) array[1];
    
        bagService.createRoleBagSlot(roleId);
    }

    @Override
    public String getEventType() {
        return EventConstants.ROLE_CREATE;
    }

}
