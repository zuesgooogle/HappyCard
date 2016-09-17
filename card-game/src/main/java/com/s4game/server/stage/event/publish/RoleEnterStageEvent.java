package com.s4game.server.stage.event.publish;

import com.s4game.core.event.IEvent;

public class RoleEnterStageEvent implements IEvent {
    
    private Object[] data;
    
    private String roleId;

    public RoleEnterStageEvent(String stageId, String mapId, String roleId) {
        this.data = new Object[] { stageId, mapId, roleId };
        this.roleId = roleId;
    }

    public String getType() {
        return "stage_role_enter";
    }

    public Object getData() {
        return this.data;
    }

    public Object getSource() {
        return this.roleId;
    }
}