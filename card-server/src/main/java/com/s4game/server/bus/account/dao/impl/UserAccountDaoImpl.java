package com.s4game.server.bus.account.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.AccessType;
import com.s4game.server.bus.account.dao.IUserAccountDao;
import com.s4game.server.bus.account.entity.UserAccount;
import com.s4game.server.bus.share.dao.BusAbsCacheDao;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月21日 下午6:12:53
 *
 */
@SuppressWarnings("unchecked")
@Component
public class UserAccountDaoImpl extends BusAbsCacheDao<UserAccount> implements IUserAccountDao {

	@Override
	public void insertUserAccount(UserAccount userAccount, String userGuid) {
		insert(userAccount, userGuid, AccessType.getDirectDbType());
	} 

	@Override
	public UserAccount initUserAccount(String userGuid, String roleId, String serverId) {
		Map<String, Object> param = new HashMap<String, Object>();
	    param.put("userGuid", userGuid);
	    param.put("serverId", serverId);
	    
	    return (UserAccount)getRecords(param, roleId, AccessType.getDirectDbType()).get(0);
	}

	@Override
	public UserAccount getUserAccountDb(String userGuid, String serverId) {
		String id = generateUserAccountId(userGuid, serverId);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		
		List<UserAccount> list = getRecords(param, userGuid, AccessType.getDirectDbType());
		if( null == list || list.isEmpty() ) {
			return null;
		}
		
		return list.get(0);
	}

	@Override
	public void updateUserAccountDb(UserAccount userAccount) {
		update(userAccount, userAccount.getId(), AccessType.getDirectDbType());
	}

	@Override
	public String generateUserAccountId(String userGuid, String serverId) {
		return userGuid + "@" + serverId;
	}

}
