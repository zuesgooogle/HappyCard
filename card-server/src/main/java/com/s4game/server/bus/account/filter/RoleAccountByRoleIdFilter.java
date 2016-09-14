package com.s4game.server.bus.account.filter;

import com.s4game.core.data.IQueryFilter;
import com.s4game.server.bus.account.entity.RoleAccount;

public class RoleAccountByRoleIdFilter implements IQueryFilter<RoleAccount> {
    
    private String roleId;
    
    private boolean found;

    public RoleAccountByRoleIdFilter(String roleId) {
        this.roleId = roleId;
    }

    public boolean check(RoleAccount roleeAccount) {
        boolean bool = roleeAccount.getUserRoleId().equals(this.roleId);
        if (bool) {
            this.found = true;
        }
        return bool;
    }

    public boolean stopped() {
        return this.found;
    }
}