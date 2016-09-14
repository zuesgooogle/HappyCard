package com.s4game.server.bus.bag.dao.filter;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.IQueryFilter;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public interface IQueryCountFilter<T extends IEntity> extends IQueryFilter<T> {
    
    /**
     * 满足条件
     * 
     * @return
     */
    public boolean isSatisfied();
    
}
