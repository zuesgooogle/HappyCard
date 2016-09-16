package com.s4game.server.bus.role.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.bus.role.command.RoleCommands;
import com.s4game.server.bus.swap.BusMsgSender;

/**
*
* @author zeusgoogogle@gmail.com
* @sine 2016年9月15日 下午9:04:12
*
*/
@ActionWorker
public class RoleAction {

	private Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	private BusMsgSender msgSender;

}
