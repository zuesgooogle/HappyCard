package com.s4game.server.public_.room.service;

import com.s4game.server.public_.room.entity.Room;

public interface IRoomService {

    /**
     * 创建房间
     * 
     * @param roleId
     * @param round
     * @param serial
     * @param win
     * @return
     */
    Room createRoom(String roleId, int round, boolean serial, boolean win);
    
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
