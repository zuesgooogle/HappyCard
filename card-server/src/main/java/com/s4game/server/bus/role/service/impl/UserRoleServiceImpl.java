package com.s4game.server.bus.role.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventService;
import com.s4game.server.bus.id.export.IdGenerator;
import com.s4game.server.bus.id.service.IIdGenService;
import com.s4game.server.bus.role.RoleModuleInfo;
import com.s4game.server.bus.role.dao.IUserRoleDao;
import com.s4game.server.bus.role.entity.UserRole;
import com.s4game.server.bus.role.event.publish.RoleCreateEvent;
import com.s4game.server.bus.role.export.IncrExpResp;
import com.s4game.server.bus.role.export.IncrZhenQiResp;
import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.role.output.CreateRoleOutput;
import com.s4game.server.bus.role.output.GetRoleOutput;
import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.bus.share.service.IRoleStateService;
import com.s4game.server.utils.GameConfigureUtil;

@Component
public class UserRoleServiceImpl implements IUserRoleService {

	@Resource
	private IUserRoleDao userRoleDao;

	@Resource
	private IIdGenService idGenService;

	@Resource
	private IdGenerator idGenerator;

	@Resource
	private IRoleStateService roleStateService;

	@Resource
	private IEventService eventService;
	
	@Override
	public String getRoleId(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserId(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getRoles(String userId, String serverId, boolean fangChenmi) {
		List<UserRole> list = userRoleDao.getRolesFromDb(userId, serverId);
		if (null != list && !list.isEmpty()) {
			return getRoles(list, fangChenmi);
		}

		return null;
	}

	private Object[] getRoles(List<UserRole> list, boolean fengChenmi) {

		Object[] result = new Object[list.size()];
		int i = 0;

		for (UserRole ur : list) {
			Integer localInteger = Integer.valueOf(fengChenmi ? 1 : 0);
			if (!localInteger.equals(ur.getIsSetFangchenmi())) {
				ur.setIsSetFangchenmi(localInteger);
				this.userRoleDao.updateRole(ur);
			}

			result[i] = GetRoleOutput.roleInfo(ur);
			i++;
		}

		return result;
	}

	@Override
	public Object createRole(String userId, String serverId, String name, String job, int sex, String face, int level, boolean fangChenmi, String platform) {
		//名称合法性，过滤词
		//nameCheck
		
		String realName = GameConfigureUtil.getServerId() + "." + name;
		
		//重名验证
		List<UserRole> list = userRoleDao.getRole(realName);
		if(null != list && !list.isEmpty()) {
			return CreateRoleOutput.nameRepeated();
		}
		
		//已经创建过角色
		list = userRoleDao.getRolesFromDb(userId, serverId);
		if( null != list && list.size() >= 4 ) { //最多允许创建4个角色
			return CreateRoleOutput.roleExisted();
		}
		
		UserRole userRole = roleInit(userId, serverId, name, job, sex, face, level, fangChenmi, platform);
		eventService.publish(new RoleCreateEvent(userRole));
		
		return CreateRoleOutput.createRoleSuccess(userRole);
	}

	private UserRole roleInit(String userId, String serverId, String name, String job, int sex, String face, int level, boolean fangChenmi, String platform) {
		UserRole userRole = new UserRole();
		
		userRole.setId(idGenerator.getId4Module(RoleModuleInfo.MODULE_NAME));
		userRole.setUserId(userId);
		userRole.setServerId(serverId);
		userRole.setName(name);
		userRole.setJob(job);

		userRole.setSex(sex);
		userRole.setFace(face);

		userRole.setLevel(level > 0 ? level : 1);

		long l = System.currentTimeMillis();
		userRole.setCreateTime(new Timestamp(l));
		userRole.setOnlineTime(Long.valueOf(l));
		userRole.setUpgradeTime(Long.valueOf(l));
		userRole.setPlatform(platform);

		userRole.setIsSetFangchenmi(Integer.valueOf(fangChenmi ? 1 : 0));

		this.userRoleDao.insertRole(userRole);

		return userRole;
	}

	@Override
	public Object[] selectRoleIdsByUserId(String userId, String serverId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleWrapper getRole(String roleId) {
		UserRole localUserRole = null;

		if (this.roleStateService.isOnline(roleId)) {
			localUserRole = (UserRole) this.userRoleDao.cacheLoad(roleId, roleId);
		} else {
			localUserRole = this.userRoleDao.getRoleByRoleId(roleId);
		}
		if (null != localUserRole) {
			return new RoleWrapper(localUserRole);
		}
		return null;
	}

	@Override
	public List<RoleWrapper> getRolesLikeName(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setChenmi(String paramString) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHalfChenmi(String paramString) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onlineChenmi(String paramString) {
		// TODO Auto-generated method stub

	}

	@Override
	public void offlineChenmi(String paramString) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getChenmiIncomeRate(String paramString) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IncrExpResp incrExp(String paramString, Long paramLong, boolean paramBoolean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void online(String paramString) {
		// TODO Auto-generated method stub

	}

	@Override
	public void offline(String paramString) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] decrZhenqi(String paramString, int paramInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncrZhenQiResp incrZhenqi(String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] queryRoleByName(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleWrapper> getRolesLikeName(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] roleEwaiInfo(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getRoleNames(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IncrExpResp upgradeLevel(String paramString, int paramInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean decrExpCheck(String paramString, Long paramLong) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void decrExp(String paramString, Long paramLong) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserRole> selectRoleInfos(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

}
