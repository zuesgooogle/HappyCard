package com.s4game.server.bus.account.service;

import com.s4game.server.bus.account.export.response.CheckResponse;
import com.s4game.server.bus.account.export.response.DecrResponse;
import com.s4game.server.bus.account.export.response.IncrResponse;
import com.s4game.server.gamerule.money.MoneyType;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年6月8日 下午6:14:50
 *
 */

public interface IAccountService {

	public void createAccount(String userGuid, String userRoleId);
	
	public Object[] getMoneyData(String roleId);
	
	/**
	 * 增加货币
	 * 
	 * @param roleId
	 * @param incr
	 * @param moneyType
	 * @param notice
	 * @return
	 */
	public IncrResponse incr(String roleId, long incr, MoneyType moneyType, boolean notice);

	/**
	 * 检查货币是否足够
	 * 
	 * @param roleId
	 * @param decr
	 * @param moneyType
	 * @return
	 */
	public CheckResponse decrCheck(String roleId, long decr, MoneyType moneyType);
	
	/**
	 * 扣除货币
	 * 
	 * @param roleId
	 * @param decr
	 * @param moneyType
	 * @param notice
	 * @return
	 */
	public DecrResponse decr(String roleId, long decr, MoneyType moneyType, boolean notice);
	
}
