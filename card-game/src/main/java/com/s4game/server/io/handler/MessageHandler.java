package com.s4game.server.io.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.s4game.server.utils.ChannelAttributeUtil;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

@Sharable
public class MessageHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

	private Logger LOG = LogManager.getLogger(getClass());
	
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
    		case LoginCommands.LOGIN_IN:
    		    channelManager.addChannel(sessionId, ctx.channel());
    		    break;
            }
            
            String userId = json.getString("userId");
    		String ip = ChannelAttributeUtil.attr(ctx.channel(), IoConstants.IP_KEY);
    		
    		
    		channelManager.addChannel(userId, ctx.channel());
    		
    		Message message = new Message(command, json, FromType.CLIENT, DestType.BUS, "", userId, sessionId, ip);
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
	    
	}
}
