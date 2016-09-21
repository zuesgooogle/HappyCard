package com.s4game.server.io.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.server.io.global.ChannelManager;
import com.s4game.server.io.message.IoMessage;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月19日 下午5:02:38
 * 
 */
@ActionWorker
public class IoMsgOutAction {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Resource
    private ChannelManager channelManager;

    @ActionMapping(mapping = IoMessage.IO_MSG_OUT_CMD)
    public void out(IoMessage message) {
        LOG.info("message out: {}", message.toString());

        int route = message.getRoute();
        switch (route) {
        case 1: // one player
            Channel channel = null;
            String sessionId = message.getSessionId();
            if (null != sessionId) {
                channel = channelManager.getChannel(sessionId);
            }

            String roleId = message.getRoleId();
            if (!StringUtils.isEmpty(roleId)) {
                channel = channelManager.getChannel(roleId);
            }

            if (null != channel) {
                JSONObject data = (JSONObject) message.getData();
                data.put("cmd", message.getRealCommand());
                
                channel.writeAndFlush( new TextWebSocketFrame(data.toJSONString()));
            }

            break;
        case 2: // mutile player
            break;
        case 3: // all player
            break;
        }

    }

}
