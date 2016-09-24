package com.s4game.server.public_.card.moduleinit;

import org.springframework.stereotype.Component;

import com.s4game.server.public_.share.PublicModuleInit;
import com.s4game.server.share.event.EventHandleCommands;

/**
*
* @author zeusgoogogle@gmail.com
* @sine 2016年9月24日 下午8:30:32
*
*/
@Component
public class CardModuleInit extends PublicModuleInit {

	@Override
	public EventHandleCommands getEventHandleCommands() {
		return null;
	}

	@Override
	protected InCmd getInCmd() {
		return null;
	}

}
