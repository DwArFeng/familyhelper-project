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
public class PermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = 5363222023564615761L;

    private LongIdKey projectKey;
    private StringIdKey userKey;

    public PermissionRemoveInfo() {
    }

    public PermissionRemoveInfo(LongIdKey projectKey, StringIdKey userKey) {
        this.projectKey = projectKey;
        this.userKey = userKey;
    }

    public LongIdKey getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(LongIdKey projectKey) {
        this.projectKey = projectKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "PermissionRemoveInfo{" +
                "projectKey=" + projectKey +
                ", userKey=" + userKey +
                '}';
    }
}
