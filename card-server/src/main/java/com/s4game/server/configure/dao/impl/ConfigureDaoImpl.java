package com.s4game.server.configure.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.IQueryFilter;
import com.s4game.core.data.accessor.AccessType;
import com.s4game.core.data.accessor.AccessorManager;
import com.s4game.core.data.accessor.IDbAccessor;
import com.s4game.server.configure.dao.IConfigureDao;
import com.s4game.server.configure.export.impl.ConfigureContext;

@SuppressWarnings("unchecked")
@Component
public class ConfigureDaoImpl implements IConfigureDao {
    
    @Resource
    private ConfigureContext configureContext;
    
    @Resource
    private AccessorManager accessorManager;

    @PostConstruct
    public void init() {
        this.configureContext.init();
    }

    public IDbAccessor getAccessor() {
        return this.accessorManager.getAccessor(AccessType.getConfigureCacheDbType());
    }

    @Override
    public void deleteAll(Class<? extends IEntity> class_) {
        List<IEntity> list = this.loadAll(class_);
        if (null != list) {
            for (IEntity iEntity : list) {
                this.getAccessor().delete(ConfigureContext.CONFIGURE_IDENTITY, iEntity.getPrimaryKeyValue(), class_);
            }
        }
    }

    @Override
    public void deleteById(Class<? extends IEntity> class_, Object object) {
        this.getAccessor().delete(ConfigureContext.CONFIGURE_IDENTITY, object, class_);
    }

    @Override
    public void insert(IEntity iEntity) {
        this.getAccessor().insert(ConfigureContext.CONFIGURE_IDENTITY, iEntity, iEntity.getClass());
    }

    @Override
    public <T extends IEntity> T get(Class<? extends IEntity> class_, Object object) {
        return (T) this.getAccessor().query(ConfigureContext.CONFIGURE_IDENTITY, object, class_);
    }

    @Override
    public <T extends IEntity> List<T> loadAll(Class<? extends IEntity> class_) {
        return (List<T>) this.getAccessor().queryList(ConfigureContext.CONFIGURE_IDENTITY, null, class_);
    }

    @Override
    public <T extends IEntity> List<T> loadAll(Class<? extends IEntity> class_, IQueryFilter<IEntity> iQueryFilter) {
        return (List<T>) this.getAccessor().queryList(ConfigureContext.CONFIGURE_IDENTITY, null, iQueryFilter, class_);
    }
}
