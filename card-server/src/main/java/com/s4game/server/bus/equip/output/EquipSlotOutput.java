package com.s4game.server.bus.equip.output;

public class EquipSlotOutput {
	
	public static Object[] getErrorCode(int errorCode, Object[] data) {
		if (data != null) {
			return new Object[] { Integer.valueOf(0), errorCode, data };
		}
		return new Object[] { Integer.valueOf(0), errorCode };
	}

	public static Object[] dressEquip(Object[] data, Integer paramInteger) {
		return new Object[] { Integer.valueOf(1), data, paramInteger };
	}
}