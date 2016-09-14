package com.s4game.server.bus.bag.dao;

import com.s4game.core.data.accessor.dao.ICacheInitDaoOperation;
import com.s4game.server.bus.bag.entity.RoleBagSlot;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月11日 下午2:58:02
 *
 */

public interface IRoleBagSlotDao extends ICacheInitDaoOperation<RoleBagSlot> {
    
    /**
     * 
     * @param roleId  userRoelId
     * @param id      
     * @return
     */
    public RoleBagSlot selectDb(String roleId, String id);

    public void insertDb(RoleBagSlot bagSlot);
    
    public boolean updateDb(RoleBagSlot bagSlot);
    
    public boolean deleteDb(String roleId, String id);
}
