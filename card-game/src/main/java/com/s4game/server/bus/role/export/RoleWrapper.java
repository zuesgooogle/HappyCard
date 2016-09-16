package com.s4game.server.bus.role.export;

import java.io.Serializable;
import java.sql.Timestamp;

import com.s4game.server.bus.role.entity.UserRole;

public class RoleWrapper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UserRole userRole;

	public RoleWrapper(UserRole paramUserRole) {
		this.userRole = paramUserRole;
	}

	public int getChenmiAddOnlineTime() {
		return this.userRole.getChenmiAddOnline().intValue();
	}

	public boolean isChenmi() {
		int i = this.userRole.getIsSetFangchenmi().intValue();
		boolean bool = false;
		if (i == 1) {
			bool = true;
		}
		return bool;
	}

	public Long getOnlineTime() {
		return this.userRole.getOnlineTime();
	}

	public Long getOfflineTime() {
		return this.userRole.getOfflineTime();
	}

	public String getId() {
		return this.userRole.getId();
	}

	public String getUserId() {
		return this.userRole.getUserId();
	}

	public String getName() {
		return this.userRole.getName();
	}

	public String getJob() {
		return this.userRole.getJob();
	}

	public Integer getSex() {
		return this.userRole.getSex();
	}

	public Long getExp() {
		return 0L;
	}

	public Integer getLevel() {
		return this.userRole.getLevel();
	}

	public String getFace() {
		return this.userRole.getFace();
	}

	public Integer getZhenqi() {
		return this.userRole.getZhenqi();
	}

	public Integer getShengwang() {
		return this.userRole.getShengwang();
	}

	public Timestamp getCreateTime() {
		return this.userRole.getCreateTime();
	}

	public boolean isSetFangchenmi() {
		return this.userRole.getIsSetFangchenmi().intValue() == 1;
	}

	public Integer getChenmiAddOnline() {
		return this.userRole.getChenmiAddOnline();
	}

	public Integer getChenmiAddOffline() {
		return this.userRole.getChenmiAddOffline();
	}

	public String getServerId() {
		return this.userRole.getServerId();
	}

	public Integer getRoleType() {
		return this.userRole.getRoleType();
	}

	public Long getUpgradeTime() {
		return this.userRole.getUpgradeTime();
	}
}

/*
 * Location: D:\jd-gui\dntg_start.jar
 * 
 * Qualified Name: com.xianling.bus.role.export.RoleWrapper
 * 
 * JD-Core Version: 0.7.0.1
 */