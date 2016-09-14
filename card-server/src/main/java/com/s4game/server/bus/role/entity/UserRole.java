package com.s4game.server.bus.role.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.s4game.core.data.AbsVersion;
import com.s4game.core.data.IEntity;

public class UserRole extends AbsVersion implements Serializable, IEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String userId;
	private String name;
	private String job;
	private Integer sex;
	private Long exp;
	private Integer level = Integer.valueOf(1);
	private String face;
	private Integer zhenqi = Integer.valueOf(0);
	private Integer shengwang = Integer.valueOf(0);
	private Timestamp createTime;
	private Long onlineTime;
	private Long offlineTime;
	private Integer isSetFangchenmi;
	private Integer chenmiAddOnline;
	private Integer chenmiAddOffline;
	private String serverId;
	private Integer roleType = Integer.valueOf(0);
	private Long upgradeTime;
	private String platform;
	private Integer zhanli;
	private Integer loginCount;
	private Long time;
	private Timestamp logUpdateTime;

	public Timestamp getLogUpdateTime() {
		return this.logUpdateTime;
	}

	public void setLogUpdateTime(Timestamp logUpdateTime) {
		this.logUpdateTime = logUpdateTime;
	}

	public Integer getZhanli() {
		return this.zhanli;
	}

	public void setZhanli(Integer zhanli) {
		this.zhanli = zhanli;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getOnlineTime() {
		return this.onlineTime;
	}

	public void setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Long getOfflineTime() {
		return this.offlineTime;
	}

	public void setOfflineTime(Long offlineTime) {
		this.offlineTime = offlineTime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getExp() {
		if (this.exp == null) {
			return Long.valueOf(0L);
		}
		return this.exp;
	}

	public void setExp(Long exp) {
		this.exp = exp;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getFace() {
		return this.face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public Integer getZhenqi() {
		return this.zhenqi;
	}

	public void setZhenqi(Integer zhenqi) {
		this.zhenqi = zhenqi;
	}

	public Integer getShengwang() {
		return this.shengwang;
	}

	public void setShengwang(Integer shengwang) {
		this.shengwang = shengwang;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getIsSetFangchenmi() {
		return this.isSetFangchenmi;
	}

	public void setIsSetFangchenmi(Integer isSetFangchenmi) {
		this.isSetFangchenmi = isSetFangchenmi;
	}

	public Integer getChenmiAddOnline() {
		if (this.chenmiAddOnline == null) {
			this.chenmiAddOnline = Integer.valueOf(0);
		}
		return this.chenmiAddOnline;
	}

	public void setChenmiAddOnline(Integer chenmiAddOnline) {
		this.chenmiAddOnline = chenmiAddOnline;
	}

	public Integer getChenmiAddOffline() {
		if (this.chenmiAddOffline == null) {
			this.chenmiAddOffline = Integer.valueOf(0);
		}
		return this.chenmiAddOffline;
	}

	public void setChenmiAddOffline(Integer chenmiAddOffline) {
		this.chenmiAddOffline = chenmiAddOffline;
	}

	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public Integer getRoleType() {
		return this.roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Long getUpgradeTime() {
		return this.upgradeTime;
	}

	public void setUpgradeTime(Long setUpgradeTime) {
		this.upgradeTime = setUpgradeTime;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPirmaryKeyName() {
		return "id";
	}

	public String getPrimaryKeyValue() {
		return getId();
	}

	public UserRole copy() {
		UserRole userRole = new UserRole();
		
		userRole.setId(getId());
		userRole.setUserId(getUserId());
		userRole.setName(getName());
		userRole.setJob(getJob());
		userRole.setSex(getSex());
		userRole.setExp(getExp());
		userRole.setLevel(getLevel());
		userRole.setFace(getFace());
		userRole.setZhenqi(getZhenqi());
		userRole.setShengwang(getShengwang());
		userRole.setCreateTime(getCreateTime());
		userRole.setOnlineTime(getOnlineTime());
		userRole.setOfflineTime(getOfflineTime());
		userRole.setIsSetFangchenmi(getIsSetFangchenmi());
		userRole.setChenmiAddOnline(getChenmiAddOnline());
		userRole.setChenmiAddOffline(getChenmiAddOffline());
		userRole.setServerId(getServerId());
		userRole.setPlatform(getPlatform());
		userRole.setRoleType(getRoleType());
		userRole.setUpgradeTime(getUpgradeTime());
		userRole.setZhanli(getZhanli());
		userRole.setLoginCount(getLoginCount());
		userRole.setTime(getTime());
		userRole.setLogUpdateTime(getLogUpdateTime());
		return userRole;
	}
}