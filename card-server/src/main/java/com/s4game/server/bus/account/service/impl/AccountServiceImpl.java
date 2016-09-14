package com.s4game.server.bus.account.service.impl;

import static com.s4game.server.gs.sycn.GsSyncComponent.COMPONENT_NAME;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.sync.annotation.Sync;
import com.s4game.server.bus.account.AccountConstants;
import com.s4game.server.bus.account.AccountModuleInfo;
import com.s4game.server.bus.account.command.AccountCommands;
import com.s4game.server.bus.account.dao.IRoleAccountDao;
import com.s4game.server.bus.account.dao.IUserAccountDao;
import com.s4game.server.bus.account.entity.RoleAccount;
import com.s4game.server.bus.account.entity.UserAccount;
import com.s4game.server.bus.account.export.response.CheckResponse;
import com.s4game.server.bus.account.export.response.DecrResponse;
import com.s4game.server.bus.account.export.response.IncrResponse;
import com.s4game.server.bus.account.filter.RoleAccountByRoleIdFilter;
import com.s4game.server.bus.account.filter.UserAccountGuIdFilter;
import com.s4game.server.bus.account.output.AccountOutPut;
import com.s4game.server.bus.account.service.IAccountService;
import com.s4game.server.bus.id.export.IdGenerator;
import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.bus.share.service.IRoleStateService;
import com.s4game.server.bus.swap.BusMsgSender;
import com.s4game.server.gamerule.money.MoneyType;
import com.s4game.server.gs.sycn.GsSyncComponent;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年6月8日 下午6:15:44
 * 
 */
@Component
public class AccountServiceImpl implements IAccountService {

    @Resource
    private IUserAccountDao userAccountDao;

    @Resource
    private IRoleAccountDao roleAccountDao;

    @Resource
    private IdGenerator idGenerator;

    @Resource
    private IUserRoleService userRoleService;

    @Resource
    private IRoleStateService roleStateService;

    @Resource
    private BusMsgSender busMsgSender;

    @Override
    public void createAccount(String userGuid, String userRoleId) {
        RoleAccount roleAccount = new RoleAccount();
        roleAccount = new RoleAccount();
        roleAccount.setId(idGenerator.getId4Module(AccountModuleInfo.MODULE_NAME));

        roleAccount.setUserRoleId(userRoleId);
        roleAccount.setTongqian(Long.valueOf(0L));
        roleAccount.setCreateTime(new Timestamp(System.currentTimeMillis()));
        roleAccount.setBindLingshi(Long.valueOf(0L));
        this.roleAccountDao.insertRoleAccount(roleAccount);

        RoleWrapper roleWrapper = userRoleService.getRole(userRoleId);
        UserAccount userAccount = this.userAccountDao.getUserAccountDb(userGuid, roleWrapper.getServerId());
        if (userAccount == null) {
            userAccount = new UserAccount();
            userAccount.setId(this.userAccountDao.generateUserAccountId(userGuid, roleWrapper.getServerId()));
            userAccount.setUserGuid(userGuid);
            userAccount.setLingshi(Long.valueOf(0L));
            userAccount.setReciveYb(Integer.valueOf(0));
            userAccount.setIsRecharge(AccountConstants.NO_RECHARGE);
            userAccount.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userAccount.setServerId(roleWrapper.getServerId());

            this.userAccountDao.insertUserAccount(userAccount, userRoleId);
        }
    }

    @Sync(component = GsSyncComponent.COMPONENT_NAME, indexes = { 0 })
    @Override
    public Object[] getMoneyData(String roleId) {
        UserAccount userAccount = getUserAccount(roleId);
        RoleAccount roleAccount = getRoleAccount(roleId);

        return AccountOutPut.getMoneyChange(userAccount, roleAccount);
    }

    private RoleAccount getRoleAccount(String roleId) {
        if (roleStateService.isOnline(roleId)) {
            List<RoleAccount> list = this.roleAccountDao.cacheLoadAll(roleId, new RoleAccountByRoleIdFilter(roleId));
            if ((list == null) || (list.size() == 0)) {
                return null;
            }
            return list.get(0);
        }
        return this.roleAccountDao.getRoleAccountDb(roleId);
    }

    private UserAccount getUserAccount(String roleId) {
        RoleWrapper roleWrapper = this.userRoleService.getRole(roleId);
        if (roleStateService.isOnline(roleId)) {
            List<UserAccount> list = this.userAccountDao.cacheLoadAll(roleId, new UserAccountGuIdFilter(roleWrapper.getUserId(), roleWrapper.getServerId()));
            if (list == null || list.size() == 0) {
                return null;
            }
            return list.get(0);
        }
        return this.userAccountDao.getUserAccountDb(roleWrapper.getUserId(), roleWrapper.getServerId());
    }

    @Sync(component = COMPONENT_NAME, indexes = { 0 })
    @Override
    public IncrResponse incr(String roleId, long incr, MoneyType moneyType, boolean notice) {
        if (incr < 0) {
            return new IncrResponse(false, moneyType, 0);
        }

        switch (moneyType) {
        case YB:
            return incrYb(roleId, incr, moneyType, notice);
        case BIND_YB:
            return incrBindYb(roleId, incr, moneyType, notice);
        case YXB:
            return incrYxb(roleId, incr, moneyType, notice);
        default:
            return null;
        }
    }

    private IncrResponse incrYb(String roleId, long incr, MoneyType moneyType, boolean notice) {
        UserAccount userAccount = getUserAccount(roleId);

        long yb = userAccount.getLingshi() + incr;
        if (yb > Long.MAX_VALUE) {
            return new IncrResponse(false, moneyType, 0);
        }

        userAccount.setLingshi(yb);
        this.userAccountDao.cacheUpdate(userAccount, roleId);

        if (notice) {
            RoleAccount roleAccount = getRoleAccount(roleId);
            busMsgSender.send2One(AccountCommands.MONRY_CHANGE, roleId, AccountOutPut.getMoneyChange(userAccount, roleAccount));
        }

        return new IncrResponse(true, moneyType, incr);
    }

    private IncrResponse incrBindYb(String roleId, long incr, MoneyType moneyType, boolean notice) {
        RoleAccount roleAccount = getRoleAccount(roleId);

        long bindYb = roleAccount.getBindLingshi() + incr;
        if (bindYb > Long.MAX_VALUE) {
            return new IncrResponse(false, moneyType, 0);
        }

        roleAccount.setBindLingshi(bindYb);
        this.roleAccountDao.cacheUpdate(roleAccount, roleId);

        if (notice) {
            UserAccount userAccount = getUserAccount(roleId);
            busMsgSender.send2One(AccountCommands.MONRY_CHANGE, roleId, AccountOutPut.getMoneyChange(userAccount, roleAccount));
        }

        return new IncrResponse(true, moneyType, incr);
    }

    private IncrResponse incrYxb(String roleId, long incr, MoneyType moneyType, boolean notice) {
        RoleAccount roleAccount = getRoleAccount(roleId);

        long tongqian = incr + roleAccount.getTongqian();
        if (tongqian > Long.MAX_VALUE) {
            return new IncrResponse(false, moneyType, 0L);
        }

        roleAccount.setTongqian(tongqian);
        this.roleAccountDao.cacheUpdate(roleAccount, roleId);

        if (notice) {
            UserAccount userAccount = getUserAccount(roleId);
            busMsgSender.send2One(AccountCommands.MONRY_CHANGE, roleId, AccountOutPut.getMoneyChange(userAccount, roleAccount));
        }

        return new IncrResponse(true, moneyType, incr);
    }

    @Sync(component = COMPONENT_NAME, indexes = { 0 })
    @Override
    public CheckResponse decrCheck(String roleId, long decr, MoneyType moneyType) {
        if (decr < 0) {
            return new CheckResponse(false, moneyType, 0);
        }

        if (null == moneyType) {
            return null;
        }

        return decrCheck0(roleId, decr, moneyType);
    }

    private CheckResponse decrCheck0(String roleId, long decr, MoneyType moneyType) {
        switch (moneyType) {
        case YB:
            return decrCheckYb(roleId, decr, moneyType);
        case BIND_YB:
            return decrCheckBindYb(roleId, decr, moneyType);
        case YXB:
            return decrCheckYxb(roleId, decr, moneyType);
        default:
            return null;
        }
    }

    private CheckResponse decrCheckYb(String roleId, long decr, MoneyType moneyType) {
        if (decr < 0L) {
            return new CheckResponse(false, moneyType, decr);
        }
        UserAccount userAccount = getUserAccount(roleId);
        long l = userAccount.getLingshi().longValue() - decr;
        if (l < 0L) {
            return new CheckResponse(false, moneyType, decr);
        }
        return new CheckResponse(true, moneyType, decr);
    }

    private CheckResponse decrCheckYxb(String roleId, long decr, MoneyType moneyType) {
        if (decr < 0L) {
            return new CheckResponse(false, moneyType, decr);
        }
        RoleAccount roleAccount = getRoleAccount(roleId);
        long l = roleAccount.getTongqian() - decr;
        if (l < 0L) {
            return new CheckResponse(false, moneyType, decr);
        }
        return new CheckResponse(true, moneyType, decr);
    }

    private CheckResponse decrCheckBindYb(String roleId, long decr, MoneyType moneyType) {
        if (decr < 0L) {
            return new CheckResponse(false, moneyType, decr);
        }
        RoleAccount roleAccount = getRoleAccount(roleId);
        long l = roleAccount.getBindLingshi() - decr;
        if (l < 0L) {
            return new CheckResponse(false, moneyType, decr);
        }
        return new CheckResponse(true, moneyType, decr);
    }

    @Override
    public DecrResponse decr(String roleId, long decr, MoneyType moneyType, boolean notice) {
        if( decr < 0 ) {
            return new DecrResponse(false, moneyType, 0);
        }
        
        CheckResponse checkResponse = decrCheck(roleId, decr, moneyType);
        if( !checkResponse.isSuccess() ) {
            return new DecrResponse(false, moneyType, decr);
        }
        
        DecrResponse decrResponse = decr(roleId, decr, moneyType);
        if( decrResponse.isSuccess() ) {
            if(notice) {
                UserAccount userAccount = getUserAccount(roleId);
                RoleAccount roleAccount = getRoleAccount(roleId);
                busMsgSender.send2One(AccountCommands.MONRY_CHANGE, roleId, AccountOutPut.getMoneyChange(userAccount, roleAccount));
            }
        }
        
        return new DecrResponse(false, moneyType, decr);
    }

    private DecrResponse decr(String roleId, long decr, MoneyType moneyType) {
        switch (moneyType) {
        case YB:
            return decrYb(roleId, decr, moneyType);
        case BIND_YB:
            return decrBindYb(roleId, decr, moneyType);
        case YXB:
            return decrYxb(roleId, decr, moneyType);
        default:
            return null;
        }
    }

    private DecrResponse decrYb(String roleId, long decr, MoneyType moneyType) {
        if (decr < 0L) {
            return new DecrResponse(false, moneyType, decr);
        }

        UserAccount userAccount = getUserAccount(roleId);
        long l = userAccount.getLingshi().longValue() - decr;
        if (l < 0L) {
            return new DecrResponse(false, moneyType, decr);
        }

        userAccount.setLingshi(l);

        this.userAccountDao.cacheUpdate(userAccount, roleId);
        return new DecrResponse(true, moneyType, decr);
    }

    private DecrResponse decrBindYb(String roleId, long decr, MoneyType moneyType) {
        if (decr < 0L) {
            return new DecrResponse(false, moneyType, decr);
        }

        RoleAccount roleAccount = getRoleAccount(roleId);
        long l = roleAccount.getBindLingshi().longValue() - decr;
        if (l < 0L) {
            return new DecrResponse(false, moneyType, decr);
        }

        roleAccount.setBindLingshi(l);

        this.roleAccountDao.cacheUpdate(roleAccount, roleId);
        return new DecrResponse(true, moneyType, decr);
    }

    private DecrResponse decrYxb(String roleId, long decr, MoneyType moneyType) {
        if (decr < 0L) {
            return new DecrResponse(false, moneyType, decr);
        }

        RoleAccount roleAccount = getRoleAccount(roleId);
        long l = roleAccount.getTongqian().longValue() - decr;
        if (l < 0L) {
            return new DecrResponse(false, moneyType, decr);
        }

        roleAccount.setTongqian(l);
        this.roleAccountDao.cacheUpdate(roleAccount, roleId);
        return new DecrResponse(true, moneyType, decr);
    }

}
