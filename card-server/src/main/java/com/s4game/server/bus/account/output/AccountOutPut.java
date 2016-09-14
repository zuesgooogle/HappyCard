package com.s4game.server.bus.account.output;

import com.s4game.server.bus.account.entity.RoleAccount;
import com.s4game.server.bus.account.entity.UserAccount;

public class AccountOutPut {
    
    public static Object[] getMoneyChange(UserAccount userAccount, RoleAccount roleAccount) {
        return new Object[] {1, roleAccount.getTongqian(), userAccount.getLingshi(), roleAccount.getBindLingshi()};
    }
    
}
