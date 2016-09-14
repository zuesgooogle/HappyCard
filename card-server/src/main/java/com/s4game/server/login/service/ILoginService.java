package com.s4game.server.login.service;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月7日 下午3:14:49
 *
 */

public interface ILoginService {
	
	/**
	 * 获取角色列表
	 * 
	 * 返回系统时间
	 * 
	 * @param userId
	 * @param serverId
	 * @param timestamp
	 * @param sign
	 * @param fangChenmi
	 */
	public Object in(String userId, String serverId, String timestamp, String sign, boolean fangChenmi);

	/**
	 * 
	 * 创建角色
	 * 
	 * @param userId
	 * @param name
	 * @param job
	 * @param sex
	 * @param face
	 * @param platform
	 */
	public Object createRole(String userId, String serverId, String name, String job, int sex, String face, boolean fangChenmi, String platform);
}
