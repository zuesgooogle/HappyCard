package com.s4game.server.configure.export.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.IQueryFilter;
import com.s4game.server.configure.dao.IConfigureDao;
import com.s4game.server.configure.export.IConfigureExportService;

@Component
public class ConfigureExportServiceImpl implements IConfigureExportService {
    
    @Autowired
    private IConfigureDao configureDao;

    @Override
    public void add(IEntity iEntity) {
        this.configureDao.insert(iEntity);
    }

    @Override
    public void deleteById(Class<? extends IEntity> class_, Object object) {
        this.configureDao.deleteById(class_, object);
    }

    @Override
    public void deleteAll(Class<? extends IEntity> class_) {
        this.configureDao.deleteAll(class_);
    }

    @Override
    public <T extends IEntity> T get(Class<? extends IEntity> class_, Object object) {
        return this.configureDao.get(class_, object);
    }

    @Override
    public <T extends IEntity> List<T> get(Class<? extends IEntity> class_) {
        return this.configureDao.loadAll(class_);
    }

    @Override
    public <T extends IEntity> List<T> getByFilter(Class<? extends IEntity> class_, IQueryFilter<IEntity> iQueryFilter) {
        return this.configureDao.loadAll(class_, iQueryFilter);
    }
}
