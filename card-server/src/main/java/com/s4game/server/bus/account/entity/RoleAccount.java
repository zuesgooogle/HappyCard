package com.s4game.server.bus.account.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.s4game.core.data.AbsVersion;
import com.s4game.core.data.IEntity;

public class RoleAccount extends AbsVersion implements Serializable, IEntity {
	
	private static final long serialVersionUID = 1L;
	private static final Timestamp CURRENT_TIMESTAMP = null;
	
	private String id;
	private String userRoleId;
	private Long tongqian;
	private Long bindLingshi;
	
	private Timestamp createTime;
	private Timestamp updateTime;
	private Timestamp logUpdateTime = CURRENT_TIMESTAMP;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Long getTongqian() {
		return this.tongqian;
	}

	public void setTongqian(Long tongqian) {
		this.tongqian = tongqian;
	}

	public Long getBindLingshi() {
		return this.bindLingshi;
	}

	public void setBindLingshi(Long bindLingshi) {
		this.bindLingshi = bindLingshi;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getLogUpdateTime() {
		return this.logUpdateTime;
	}

	public void setLogUpdateTime(Timestamp logUpdateTime) {
		this.logUpdateTime = logUpdateTime;
	}

	public String getPirmaryKeyName() {
		return "id";
	}

	public String getPrimaryKeyValue() {
		return getId();
	}

	public RoleAccount copy() {
		RoleAccount roleAccount = new RoleAccount();
		roleAccount.setId(getId());
		roleAccount.setUserRoleId(getUserRoleId());
		roleAccount.setTongqian(getTongqian());
		roleAccount.setBindLingshi(getBindLingshi());
		roleAccount.setCreateTime(getCreateTime());
		roleAccount.setUpdateTime(getUpdateTime());
		roleAccount.setLogUpdateTime(getLogUpdateTime());
		return roleAccount;
	}
}