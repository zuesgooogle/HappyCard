package com.s4game.server.bus.account.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.s4game.core.data.AbsVersion;
import com.s4game.core.data.IEntity;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月21日 下午6:01:04
 * 
 */

public class UserAccount extends AbsVersion implements Serializable, IEntity {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String userGuid;
	private Long lingshi;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String serverId;
	private Integer reciveYb;
	private Integer isRecharge;
	private Timestamp logUpdateTime;

	public String getId() {
		return this.id;
	}

	public void setId(String paramString) {
		this.id = paramString;
	}

	public String getUserGuid() {
		return this.userGuid;
	}

	public void setUserGuid(String paramString) {
		this.userGuid = paramString;
	}

	public Long getLingshi() {
		return this.lingshi;
	}

	public void setLingshi(Long paramLong) {
		this.lingshi = paramLong;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp paramTimestamp) {
		this.createTime = paramTimestamp;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp paramTimestamp) {
		this.updateTime = paramTimestamp;
	}

	public String getServerId() {
		return this.serverId;
	}

	public void setServerId(String paramString) {
		this.serverId = paramString;
	}

	public Integer getReciveYb() {
		return this.reciveYb;
	}

	public void setReciveYb(Integer paramInteger) {
		this.reciveYb = paramInteger;
	}

	public Integer getIsRecharge() {
		return this.isRecharge;
	}

	public void setIsRecharge(Integer paramInteger) {
		this.isRecharge = paramInteger;
	}

	public Timestamp getLogUpdateTime() {
		return this.logUpdateTime;
	}

	public void setLogUpdateTime(Timestamp paramTimestamp) {
		this.logUpdateTime = paramTimestamp;
	}

	@Override
	public String getPirmaryKeyName() {
		return "id";
	}

	@Override
	public Object getPrimaryKeyValue() {
		return getId();
	}

	public UserAccount copy() {
		UserAccount userAccount = new UserAccount();
		userAccount.setId(getId());
		userAccount.setUserGuid(getUserGuid());
		userAccount.setLingshi(getLingshi());
		userAccount.setCreateTime(getCreateTime());
		userAccount.setUpdateTime(getUpdateTime());
		userAccount.setServerId(getServerId());
		userAccount.setReciveYb(getReciveYb());
		userAccount.setIsRecharge(getIsRecharge());
		userAccount.setLogUpdateTime(getLogUpdateTime());

		return userAccount;
	}
}
