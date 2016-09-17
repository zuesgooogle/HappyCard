package com.s4game.server.bus.stagecontroll.output;

import org.springframework.stereotype.Component;

import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.stage.export.RoleStageWrapper;

@Component
public class StageControllOutput {
    
	public static Object login(RoleWrapper roleWrapper, RoleStageWrapper roleStageWrapper, int vipLevel, Object[] chargeInfo, int gmState) {
//		return new Object[] {
//				1,
//				new Object[] { roleWrapper.getFace(), roleWrapper.getLevel(), roleWrapper.getExp(), roleWrapper.getSex(),
//						roleWrapper.getJob(), Boolean.valueOf(roleWrapper.isChenmi()),
//						vipLevel, chargeInfo, gmState },
//				new Object[] { roleStageWrapper.getMapId(), roleStageWrapper.getMapX(), roleStageWrapper.getMapY() } };
	    return null;
	}

	public static Object[] applyChangeMap(String mapId, int x, int y) {
		return new Object[] { 1, mapId, x, y };
	}

}