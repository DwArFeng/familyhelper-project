package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 工程权限信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class PermissionUpsertInfo implements Dto {

    private static final long serialVersionUID = -7330248558844177835L;

    private LongIdKey projectKey;
    private StringIdKey userKey;
    private int permissionLevel;

    public PermissionUpsertInfo() {
    }

    public PermissionUpsertInfo(LongIdKey ProjectKey, StringIdKey userKey, int permissionLevel) {
        this.projectKey = ProjectKey;
        this.userKey = userKey;
        this.permissionLevel = permissionLevel;
    }

    public LongIdKey getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(LongIdKey ProjectKey) {
        this.projectKey = ProjectKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "PermissionUpsertInfo{" +
                "projectKey=" + projectKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
