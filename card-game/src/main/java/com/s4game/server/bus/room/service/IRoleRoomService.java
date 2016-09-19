package com.s4game.server.bus.room.service;

import com.s4game.server.bus.room.entity.RoleRoom;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午1:07:49 
*
*/
public interface IRoleRoomService {

    void online(String roleId);
    
    RoleRoom createRoom(String roleId, int round, boolean serial, boolean win);
    
    /**
     * 创建房间副本
     * 
     * @param roleId
     * @param stageId
     * @param mapId
     */
    void createRoomCopy(String roleId, String stageId, String mapId, Object[] additionalData);
    
    /**
     * 进入房间
     * 
     * @param roleId
     * @param roomId
     * @param msgQueue
     */
    void enterRoom(String roleId, String roomId);
}
