package com.s4game.server.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.bus.server.service.IServerInfoService;
import com.s4game.server.login.output.InOutput;
import com.s4game.server.login.service.ILoginService;
import com.s4game.server.utils.GameConfigureUtil;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年6月8日 下午3:45:26
 *
 */
@Component
public class LoginServiceImpl implements ILoginService {

	@Resource
	private IUserRoleService userRoleService;
	
	@Resource
	private IServerInfoService serverInfoService;
	
	@Override
	public Object in(String userId, String serverId, String timestamp, String sign, boolean fangChenmi) {
		String serverTag = GameConfigureUtil.getServerId() + "-" + GameConfigureUtil.getServerVersion();
		
		Object roles = userRoleService.getRoles(userId, serverId, fangChenmi);
		
		return InOutput.success(serverTag, serverInfoService.getServerStartTime(), serverInfoService.getServerHefuTime(), roles);
	}

	@Override
	public Object createRole(String userId, String serverId, String name, String job, int sex, String face, boolean fangChenmi, String platform) {
		int level = 1;
		return userRoleService.createRole(userId, serverId, name, job, sex, face, level, fangChenmi, platform);
	}

}
