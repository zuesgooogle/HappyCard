package com.s4game.server.bus.account.dao;

import com.s4game.core.data.accessor.dao.ICacheInitDaoOperation;
import com.s4game.server.bus.account.entity.RoleAccount;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年6月4日 下午3:33:32
 * 
 */

public interface IRoleAccountDao extends ICacheInitDaoOperation<RoleAccount> {

	public void insertRoleAccount(RoleAccount roleAccount);

	public RoleAccount getRoleAccountDb(String userRoleId);

	public void updateRoleAccountDb(RoleAccount roleAccount);

}
