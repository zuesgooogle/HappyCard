package com.s4game.server.login.output;

import java.sql.Timestamp;
import java.util.TimeZone;

public class InOutput {
	public static Object accountForbitten() {
		return new Object[] { Integer.valueOf(0), LoginErrorCode.CHECK_FAILED };
	}

	public static Object signError() {
		return new Object[] { Integer.valueOf(0), LoginErrorCode.IS_FENGHAO };
	}

	public static Object success(String serverTag, Timestamp serverStartTime, Timestamp serverHefuTime, Object result) {
		return new Object[] {
				Integer.valueOf(1),
				new Object[] { 
					Long.valueOf(serverStartTime.getTime()), 
					Long.valueOf(System.currentTimeMillis()), 
					serverTag, 
					TimeZone.getDefault(),
					true, 
					serverHefuTime == null ? null : Long.valueOf(serverHefuTime.getTime()) 
				},
				new Object[] { Integer.valueOf(0), Integer.valueOf(0) }, 
				result 
			};
	}
}
