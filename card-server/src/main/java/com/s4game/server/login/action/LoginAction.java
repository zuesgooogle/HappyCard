package com.s4game.server.login.action;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.login.commond.LoginCommands;
import com.s4game.server.login.service.ILoginService;
import com.s4game.server.public_.swap.PublicMsgSender;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月7日 上午11:14:43
 *
 */
@ActionWorker
public class LoginAction {

    private Logger LOG = LogManager.getLogger(getClass());
	
	@Resource
	private PublicMsgSender msgSender;
	
	@Resource
	private ILoginService loginService;
	
	@ActionMapping(mapping = LoginCommands.IN )
	public void in(Message message) {
		LOG.info(message.toString());
		
		Object[] data = (Object[]) message.getData();
		String userId = (String)data[0];
		String serverId = (String)data[1];
		
		String timestamp = (String)data[2];
		String sign = (String)data[3];
		boolean fangChenmi = false;

		Object result = loginService.in(userId, serverId, timestamp, sign, fangChenmi);
		
		msgSender.send2OneBySessionId(message.getCommand(), userId, message.getSessionId(), result);
	}
	
	@ActionMapping(mapping = LoginCommands.CREATE_ROLE )
	public void createRole(Message message) {
		
		Object[] data = (Object[]) message.getData();
		String userId = (String)data[0];
		String serverId = (String)data[1];
		String name = (String)data[2];
		String job = (String)data[3];
		int sex = (Integer)data[4];
		String face = (String)data[5];
		String platform = (String)data[6];
		
		boolean fangChenmi = false;
		
		Object result = loginService.createRole(userId, serverId, name, job, sex, face, fangChenmi, platform);
		msgSender.send2OneBySessionId(message.getCommand(), userId, message.getSessionId(), result);
	}
	
}
