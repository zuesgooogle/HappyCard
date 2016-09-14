package com.s4game.server.bus.role.export.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.server.bus.role.entity.UserRole;
import com.s4game.server.bus.role.export.IRoleExportService;
import com.s4game.server.bus.role.export.IncrExpResp;
import com.s4game.server.bus.role.export.IncrZhenQiResp;
import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.bus.role.service.IUserRoleService;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月2日 下午5:04:51
 *
 */
@Component
public class RoleExportServiceImpl implements IRoleExportService {

    @Resource
    private IUserRoleService userRoleService;
    
    @Override
    public String getRoleId(String name) {
        return userRoleService.getRoleId(name);
    }

    @Override
    public String getUserId(String name) {
        return userRoleService.getUserId(name);
    }

    @Override
    public Object getRoles(String userId, String serverId, boolean fangChenmi) {
        return userRoleService.getRoles(userId, serverId, fangChenmi);
    }

    @Override
    public Object createRole(String userId, String serverId, String name, String job, int sex, String face, int level, boolean fangChenmi, String platform) {
        return userRoleService.createRole(userId, serverId, name, job, sex, face, level, fangChenmi, platform);
    }

    @Override
    public Object[] selectRoleIdsByUserId(String userId, String serverId) {
        return userRoleService.selectRoleIdsByUserId(userId, serverId);
    }

    @Override
    public RoleWrapper getRole(String roleId) {
        return userRoleService.getRole(roleId);
    }

    @Override
    public List<RoleWrapper> getRolesLikeName(String paramString) {
        return userRoleService.getRolesLikeName(paramString);
    }

    @Override
    public void setChenmi(String paramString) {
        userRoleService.setChenmi(paramString);
    }

    @Override
    public void setHalfChenmi(String paramString) {
        userRoleService.setHalfChenmi(paramString);
    }

    @Override
    public void onlineChenmi(String paramString) {
        userRoleService.onlineChenmi(paramString);
    }

    @Override
    public void offlineChenmi(String paramString) {
        userRoleService.offlineChenmi(paramString);
    }

    @Override
    public float getChenmiIncomeRate(String paramString) {
        return userRoleService.getChenmiIncomeRate(paramString);
    }

    @Override
    public IncrExpResp incrExp(String paramString, Long paramLong, boolean paramBoolean) {
        return userRoleService.incrExp(paramString, paramLong, paramBoolean);
    }

    @Override
    public void online(String paramString) {
        userRoleService.online(paramString);
    }

    @Override
    public void offline(String paramString) {
        userRoleService.offline(paramString);
    }

    @Override
    public Object[] decrZhenqi(String paramString, int paramInt) {
        return userRoleService.decrZhenqi(paramString, paramInt);
    }

    @Override
    public IncrZhenQiResp incrZhenqi(String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
        return userRoleService.incrZhenqi(paramString, paramInt, paramBoolean1, paramBoolean2);
    }

    @Override
    public Object[] queryRoleByName(String paramString) {
        return userRoleService.queryRoleByName(paramString);
    }

    @Override
    public List<RoleWrapper> getRolesLikeName(String paramString1, String paramString2) {
        return userRoleService.getRolesLikeName(paramString1, paramString2);
    }

    @Override
    public Object[] roleEwaiInfo(String paramString) {
        return userRoleService.roleEwaiInfo(paramString);
    }

    @Override
    public String[] getRoleNames(String paramString1, String paramString2) {
        return userRoleService.getRoleNames(paramString1, paramString2);
    }

    @Override
    public IncrExpResp upgradeLevel(String paramString, int paramInt) {
        return userRoleService.upgradeLevel(paramString, paramInt);
    }

    @Override
    public boolean decrExpCheck(String paramString, Long paramLong) {
        return userRoleService.decrExpCheck(paramString, paramLong);
    }

    @Override
    public void decrExp(String paramString, Long paramLong) {
        userRoleService.decrExp(paramString, paramLong);
    }

    @Override
    public List<UserRole> selectRoleInfos(String paramString1, String paramString2) {
        return userRoleService.selectRoleInfos(paramString1, paramString2);
    }

}
