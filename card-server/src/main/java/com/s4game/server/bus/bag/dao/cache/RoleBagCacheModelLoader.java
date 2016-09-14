package com.s4game.server.bus.bag.dao.cache;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCache;
import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.server.bus.bag.dao.IRoleBagSlotDao;
import com.s4game.server.bus.bag.entity.RoleBagSlot;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年8月11日 下午3:08:57
 *
 */
@Component
public class RoleBagCacheModelLoader implements IEntityCacheModelLoader {

    @Resource
    private IRoleBagSlotDao roleBagSlotDao;
    
    @Override
    public void load(String id, IEntityCache entityCache) {
        entityCache.addData(roleBagSlotDao.loadCacheFromDb(id), RoleBagSlot.class);
    }

}
