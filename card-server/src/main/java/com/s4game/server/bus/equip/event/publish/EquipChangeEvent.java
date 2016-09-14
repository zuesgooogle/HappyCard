package com.s4game.server.bus.equip.event.publish;

import com.s4game.core.event.IEvent;

public class EquipChangeEvent implements IEvent {
	
	private Object[] data;

	public EquipChangeEvent(String roleId, Object[] data) {
		this.data = new Object[] { roleId, data };
	}

	public String getType() {
		return "equip_change";
	}

	public Object getData() {
		return this.data;
	}

	public Object getSource() {
		return this.data[0];
	}
}