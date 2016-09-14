package com.s4game.server.bus.account.event.subscribe;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.bus.account.service.IAccountService;
import com.s4game.server.share.event.EventConstants;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年6月8日 下午6:13:34
 *
 */
@Component
public class AccountEventHandler implements IEventHandler {

	@Resource
	private IAccountService accountService;
	
	@Override
	public void handle(Object source, Object data) {
		Object[] t = (Object[])data;
		String userGuid = (String)t[0];
		String userRoleId = (String)t[1];
		
		accountService.createAccount(userGuid, userRoleId);
	}

	@Override
	public String getEventType() {
		return EventConstants.ROLE_CREATE;
	}

}
