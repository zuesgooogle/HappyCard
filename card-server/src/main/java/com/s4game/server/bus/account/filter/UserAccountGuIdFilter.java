package com.s4game.server.bus.account.filter;

import com.s4game.core.data.IQueryFilter;
import com.s4game.server.bus.account.entity.UserAccount;

public class UserAccountGuIdFilter implements IQueryFilter<UserAccount> {
    
    private String userId;
    private String serverId;
    private boolean found;

    public UserAccountGuIdFilter(String userId, String serverId) {
        this.userId = userId;
        this.serverId = serverId;
    }

    public boolean check(UserAccount userAccount) {
        if ((userAccount.getUserGuid().equals(this.userId)) && (userAccount.getServerId().equals(this.serverId))) {
            this.found = true;
            return true;
        }
        return false;
    }

    public boolean stopped() {
        return this.found;
    }
}