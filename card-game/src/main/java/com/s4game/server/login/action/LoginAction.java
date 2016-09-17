package com.s4game.server.login.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.bus.init.export.InitExportService;
import com.s4game.server.bus.role.entity.UserRole;
import com.s4game.server.io.IoConstants;
import com.s4game.server.io.global.ChannelManager;
import com.s4game.server.login.commond.LoginCommands;
import com.s4game.server.login.output.InOutput;
import com.s4game.server.login.service.ILoginService;
import com.s4game.server.public_.swap.PublicMsgSender;
import com.s4game.server.utils.ChannelAttributeUtil;

import io.netty.channel.Channel;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月7日 上午11:14:43
 *
 */
@ActionWorker
public class LoginAction {

    private Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	private PublicMsgSender msgSender;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private InitExportService initExportService;
	
    @Autowired
    private ChannelManager channelManager;

	
	@ActionMapping(mapping = LoginCommands.LOGIN_IN )
	public void in(Message message) {
		LOG.info(message.toString());
		
		JSONObject data = (JSONObject) message.getData();
		String userId = data.getString("userId");
		String name = "USER-" + System.currentTimeMillis();
		String serverId = "1";
		
		String timestamp = System.currentTimeMillis() + "";
		String sign = "";

		UserRole userRole = loginService.in(userId, serverId, name, timestamp, sign);
		msgSender.send2OneBySessionId(message.getCommand(), userRole.getId(), message.getSessionId(), InOutput.success(userRole));

		//bind channel
		Channel channel = channelManager.getSessions().get(message.getSessionId());
		channelManager.addChannel(userRole.getId(), channel);
		ChannelAttributeUtil.attr(channel, IoConstants.ROLE_KEY, userRole.getId());
		
		initExportService.roleIn(userRole.getId(), message.getIp());
	}
}
