package com.s4game.server.bus.room.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.bus.room.command.RoomCommands;
import com.s4game.server.bus.room.entity.RoleRoom;
import com.s4game.server.bus.room.output.RoomOutput;
import com.s4game.server.bus.room.service.IRoleRoomService;
import com.s4game.server.bus.swap.BusMsgSender;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月17日 下午12:07:31
 *
 */
@ActionWorker
public class RoomAction {

    private Logger LOG = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private BusMsgSender msgSender;
    
    @Autowired
    private IRoleRoomService roleRoomService;

    @ActionMapping(mapping = RoomCommands.CREATE_ROOM)
    public void create(Message message) {
        LOG.info(message.toString());

        JSONObject data = (JSONObject) message.getData();
        String roleId = message.getRoleId();
        
        int round = data.getIntValue("round");
        boolean serial = data.getBooleanValue("serial");
        boolean win = data.getBooleanValue("win");
        
        RoleRoom room = roleRoomService.createRoom(roleId, round, serial, win);
        msgSender.send2One(message.getCommand(), roleId, RoomOutput.room(room));
    }
}
