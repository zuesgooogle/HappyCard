package com.s4game.server.io.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.s4game.core.message.Message;
import com.s4game.core.message.Message.DestType;
import com.s4game.core.message.Message.FromType;
import com.s4game.protocol.Message.Request;
import com.s4game.server.bus.stagecontroll.command.StageControllCommands;
import com.s4game.server.io.IoConstants;
import com.s4game.server.io.global.ChannelManager;
import com.s4game.server.io.swap.IoMsgSender;
import com.s4game.server.login.commond.LoginCommands;
import com.s4game.server.public_.nodecontrol.command.NodeControlCommands;
import com.s4game.server.utils.ChannelAttributeUtil;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月5日 下午7:10:58
 * 
 */
@Sharable
@Component
public class NetHandler extends SimpleChannelInboundHandler<Request> {

	private Logger LOG = LogManager.getLogger(getClass());
	
	@Resource
	private ChannelManager channelManager;

	@Resource
	private IoMsgSender msgSender;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LOG.info("channel active: " + ctx.toString());
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		String roleId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY);

		if (null != roleId) {
			channelManager.removeChannel(roleId);
			
			exitNotify(ctx);
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
		LOG.info("server receive message: {}", msg.toString());

		JSONArray array = JSONArray.parseArray(msg.getData());
		if( null == array ) {
		    array = new JSONArray();
		}
		
		String sessionId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.SESSION_KEY);
		String command = msg.getCommand();
		
		switch(command) {
		case LoginCommands.IN:
		    channelManager.addChannel(sessionId, ctx.channel());
		    
		    break;
		case StageControllCommands.LOGIN:
		    command = NodeControlCommands.ROLE_IN;
            
            String roleId = array.getString(0);
            ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY, roleId);
            
            channelManager.addChannel(roleId, ctx.channel());
		    break;
		}
		
		String roleId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY);
		String ip = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.IP_KEY);
		
		//Object[] message = new Object[]{command, array.toArray(), DestType.BUS.getValue(), FromType.CLIENT.getValue(), null, sessionId, roleId, null, null, null, ip};
		
		Message message = new Message(command, array.toArray(), FromType.CLIENT, DestType.BUS, roleId, null, sessionId, ip);
		msgSender.swap(message);
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		String roleId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY);

		if (null != roleId) {
			LOG.info("role: {} disconnect.", roleId);
		}
	}

	/**
	 * 退出服务
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	private void exitNotify(ChannelHandlerContext ctx) throws Exception {
	    Request.Builder exit = Request.newBuilder();
	    exit.setCommand(NodeControlCommands.ROLE_OUT)
	        .setData("[]");
	
	    channelRead0(ctx, exit.build());
	}
}
