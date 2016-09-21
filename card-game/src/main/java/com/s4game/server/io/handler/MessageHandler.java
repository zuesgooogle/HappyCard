package com.s4game.server.io.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.s4game.core.message.Message;
import com.s4game.core.message.Message.DestType;
import com.s4game.core.message.Message.FromType;
import com.s4game.server.io.IoConstants;
import com.s4game.server.io.global.ChannelManager;
import com.s4game.server.io.swap.IoMsgSender;
import com.s4game.server.login.commond.LoginCommands;
import com.s4game.server.public_.nodecontrol.command.NodeControlCommands;
import com.s4game.server.utils.ChannelAttributeUtil;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

@Sharable
public class MessageHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ChannelManager channelManager;

	@Autowired
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
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        // ping and pong frames already handled
        if (frame instanceof TextWebSocketFrame) {
            String request = ((TextWebSocketFrame) frame).text();
            
            JSONObject json = JSON.parseObject(request);
            String command = json.getString("cmd");
            
            String sessionId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.SESSION_KEY);
            switch(command) {
    		case LoginCommands.IN:
    		    String userId = json.getString("userId");
    		    ChannelAttributeUtil.attr(ctx.channel(), IoConstants.USER_KEY, userId);
    		    
    		    channelManager.addChannel(sessionId, ctx.channel());
    		    break;
    		case LoginCommands.LOGIN_IN:
    		    command = NodeControlCommands.ROLE_IN;

    		    String roleId = json.getString("roleId");
    		    ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY, roleId);
    		    
    		    channelManager.addChannel(roleId, ctx.channel());
    		    break;
            }
            
    		String ip = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.IP_KEY);
    		String roleId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY);
    		String userId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.USER_KEY);
    		
    		Message message = new Message(command, json, FromType.CLIENT, DestType.BUS, roleId, userId, sessionId, ip);
    		msgSender.swap(message);
            
        } else {
            String message = "unsupported frame type: " + frame.getClass().getName();
            throw new UnsupportedOperationException(message);
        }
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
	    String roleId = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.ROLE_KEY);
	    
	    JSONObject json = new JSONObject();
	    json.put("cmd", NodeControlCommands.ROLE_OUT);
	    json.put("roleId", roleId);
	    
	    channelRead0(ctx, new TextWebSocketFrame(json.toJSONString()));
	}
}
