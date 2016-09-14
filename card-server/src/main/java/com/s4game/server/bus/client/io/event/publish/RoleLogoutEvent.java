package com.s4game.server.bus.client.io.event.publish;

import com.s4game.core.event.IEvent;
import com.s4game.server.share.event.EventConstants;

public class RoleLogoutEvent implements IEvent {
    
    private Object[] data = null;

    public RoleLogoutEvent(String paramString1, String paramString2) {
        this.data = new Object[] { paramString1, paramString2 };
    }

    public String getType() {
        return EventConstants.ROLE_LOGOUT;
    }

    public Object getData() {
        return this.data;
    }

    public Object getSource() {
        return this.data[0];
    }
}
