package com.s4game.server.public_.nodecontrol.service;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年6月11日 下午3:59:40
 * 
 */
public interface INodeControlService {

	public void change2online(String userRoleId);

	public void change2offline(String userRoleId);

	public void nodeLogin(String roleId, String ip);

	public void nodeExit(String roleId, String ip);

	public void nodeExitOnServerClose(String roleId);

}
