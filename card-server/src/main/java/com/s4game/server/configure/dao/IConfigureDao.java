package com.s4game.server.configure.dao;

import java.util.List;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.IQueryFilter;

public interface IConfigureDao {
    
    public void deleteAll(Class<? extends IEntity> entity);

    public void insert(IEntity entity);

    public <T extends IEntity> T get(Class<? extends IEntity> entity, Object id);

    public <T extends IEntity> List<T> loadAll(Class<? extends IEntity> entity);

    public <T extends IEntity> List<T> loadAll(Class<? extends IEntity> entity, IQueryFilter<IEntity> queryFilter);

    public void deleteById(Class<? extends IEntity> entity, Object id);
}

