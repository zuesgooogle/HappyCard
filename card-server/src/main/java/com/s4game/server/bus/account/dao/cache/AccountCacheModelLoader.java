package com.s4game.server.bus.account.dao.cache;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCache;
import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.server.bus.account.dao.IRoleAccountDao;
import com.s4game.server.bus.account.dao.IUserAccountDao;
import com.s4game.server.bus.account.entity.RoleAccount;
import com.s4game.server.bus.account.entity.UserAccount;
import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.role.service.IUserRoleService;

@Component
public class AccountCacheModelLoader implements IEntityCacheModelLoader {
    
    @Resource
    private IRoleAccountDao roleAccountDao;
    
    @Resource
    private IUserAccountDao userAccountDao;
    
    @Resource
    private IUserRoleService userRoleService;

    public void load(String roleId, IEntityCache entityCache) {
        entityCache.addData(this.roleAccountDao.loadCacheFromDb(roleId), RoleAccount.class);
        
        RoleWrapper roleWrapper = this.userRoleService.getRole(roleId);
        entityCache.addData(this.userAccountDao.initUserAccount(roleWrapper.getUserId(), roleWrapper.getId(), roleWrapper.getServerId()), UserAccount.class);
    }
}