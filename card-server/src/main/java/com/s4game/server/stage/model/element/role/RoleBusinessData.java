package com.s4game.server.stage.model.element.role;

import java.io.Serializable;

import com.s4game.server.bus.role.export.RoleWrapper;

public class RoleBusinessData implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer level;
    private RoleWrapper loginRole;

    private String teamId;
    private String teamBuff;
    private String teamLeaderId;
    private String[] teamMembers;

    private int vipLevel;
    private int jingjieId;
    private int dropCountRate = 1;
    private long fightRecord;
    private Object[] clientFight;

    private String taskChapterId;
    
    /**
     * exp 各方加成
     */
    private float expOdds;
    
    private String chenghaoId;
    private String touxian;

    private String marryName;

    public RoleBusinessData(RoleWrapper roleWrapper) {
        this.loginRole = roleWrapper;
        this.level = roleWrapper.getLevel();
    }

    public String getRoleId() {
        return this.loginRole.getId();
    }

    public String getName() {
        return this.loginRole.getName();
    }

    public int getLevel() {
        return this.level.intValue();
    }

    public String getJob() {
        return this.loginRole.getJob();
    }

    public Integer getSex() {
        return this.loginRole.getSex();
    }

    public String getFace() {
        return this.loginRole.getFace();
    }

    public Long getOfflineTime() {
        return this.loginRole.getOfflineTime();
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTeam(String teamId, String teamLeaderId, String[] teamMembers) {
        this.teamId = teamId;
        this.teamLeaderId = teamLeaderId;
        this.teamMembers = teamMembers;
    }

    public String getTeamId() {
        return this.teamId;
    }

    public String getTeamLeaderId() {
        return this.teamLeaderId;
    }

    public String[] getTeamMembers() {
        return this.teamMembers;
    }

    public String getTeamBuff() {
        return this.teamBuff;
    }

    public void setTeamBuff(String teamBuff) {
        this.teamBuff = teamBuff;
    }

    public int getTeamCount() {
        if (null != getTeamId()) {
            return getTeamMembers().length;
        }
        return 1;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Integer getVipLevel() {
        return this.vipLevel;
    }

    public int getJingjieId() {
        return this.jingjieId;
    }

    public void setJingjieId(int jingjieId) {
        this.jingjieId = jingjieId;
    }

    public int getDropCountRate() {
        return this.dropCountRate;
    }

    public void setDropCountRate(int dropCountRate) {
        this.dropCountRate = dropCountRate;
    }

    public long getFightRecord() {
        return this.fightRecord;
    }

    public void setFightRecord(long fightRecord) {
        this.fightRecord = fightRecord;
    }

    public String getTaskChapterId() {
        return this.taskChapterId;
    }

    public void setTaskChapterId(String taskChapterId) {
        this.taskChapterId = taskChapterId;
    }

    public Object[] getClientFight() {
        return this.clientFight;
    }

    public void setClientFight(Object[] clientFight) {
        this.clientFight = clientFight;
    }

    public String getChenghaoId() {
        return this.chenghaoId;
    }

    public void setChenghaoId(String chenghaoId) {
        this.chenghaoId = chenghaoId;
    }

    public String getTouxian() {
        return this.touxian;
    }

    public void setTouxian(String touxian) {
        this.touxian = touxian;
    }

    public int getZhenqi() {
        return this.loginRole.getZhenqi().intValue();
    }

    public String getMarryName() {
        return this.marryName;
    }

    public void setMarryName(String marryName) {
        this.marryName = marryName;
    }

    public float getExpOdds() {
        return this.expOdds < 0.0F ? 0.0F : this.expOdds;
    }
    
    public void incrExpOdds(float expOdds) {
        this.expOdds += expOdds;
    }
    
    public void decrExpOdds(float expOdds) {
        this.expOdds -= expOdds;
    }
}