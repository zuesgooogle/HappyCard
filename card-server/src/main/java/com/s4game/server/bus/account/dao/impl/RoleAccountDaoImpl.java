package com.s4game.server.bus.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.AccessType;
import com.s4game.server.bus.account.dao.IRoleAccountDao;
import com.s4game.server.bus.account.entity.RoleAccount;
import com.s4game.server.bus.share.dao.BusAbsCacheDao;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年6月4日 下午3:35:00
 * 
 */
@Component
public class RoleAccountDaoImpl extends BusAbsCacheDao<RoleAccount> implements IRoleAccountDao {

	@Override
	public void insertRoleAccount(RoleAccount roleAccount) {
		insert(roleAccount, roleAccount.getUserRoleId(), AccessType.getDirectDbType());
	}

	@Override
	public RoleAccount getRoleAccountDb(String userRoleId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userRoleId", userRoleId);

		List<RoleAccount> list = getRecords(param, userRoleId, AccessType.getDirectDbType());
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void updateRoleAccountDb(RoleAccount roleAccount) {
		update(roleAccount, roleAccount.getId(), AccessType.getDirectDbType());
	}

}
