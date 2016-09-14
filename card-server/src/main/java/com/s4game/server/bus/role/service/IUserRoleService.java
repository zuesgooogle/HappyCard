package com.s4game.server.bus.role.service;

import java.util.List;

import com.s4game.server.bus.role.entity.UserRole;
import com.s4game.server.bus.role.export.IncrExpResp;
import com.s4game.server.bus.role.export.IncrZhenQiResp;
import com.s4game.server.bus.role.export.RoleWrapper;

public interface IUserRoleService {
	
	public String getRoleId(String name);

	public String getUserId(String name);

	public Object getRoles(String userId, String serverId, boolean fangChenmi);

	public Object createRole(String userId, String serverId, String name, String job, int sex, String face, int level, boolean fangChenmi, String platform);

	public Object[] selectRoleIdsByUserId(String userId, String serverId);

	public RoleWrapper getRole(String roleId);

	public List<RoleWrapper> getRolesLikeName(String paramString);

	public void setChenmi(String paramString);

	public void setHalfChenmi(String paramString);

	public void onlineChenmi(String paramString);

	public void offlineChenmi(String paramString);

	public float getChenmiIncomeRate(String paramString);

	public IncrExpResp incrExp(String paramString, Long paramLong, boolean paramBoolean);

	public void online(String paramString);

	public void offline(String paramString);

	public Object[] decrZhenqi(String paramString, int paramInt);

	public IncrZhenQiResp incrZhenqi(String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2);

	public Object[] queryRoleByName(String paramString);

	public List<RoleWrapper> getRolesLikeName(String paramString1, String paramString2);

	public Object[] roleEwaiInfo(String paramString);

	public String[] getRoleNames(String paramString1, String paramString2);

	public IncrExpResp upgradeLevel(String paramString, int paramInt);

	public boolean decrExpCheck(String paramString, Long paramLong);

	public void decrExp(String paramString, Long paramLong);

	public List<UserRole> selectRoleInfos(String paramString1, String paramString2);
}