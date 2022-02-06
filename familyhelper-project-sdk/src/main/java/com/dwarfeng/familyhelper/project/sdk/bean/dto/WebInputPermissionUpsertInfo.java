package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 工程权限信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputPermissionUpsertInfo implements Dto {

    private static final long serialVersionUID = -7453438484375735553L;

    public static PermissionUpsertInfo toStackBean(WebInputPermissionUpsertInfo webInputPermissionUpsertInfo) {
        if (Objects.isNull(webInputPermissionUpsertInfo)) {
            return null;
        } else {
            return new PermissionUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInputPermissionUpsertInfo.getProjectKey()),
                    WebInputStringIdKey.toStackBean(webInputPermissionUpsertInfo.getUserKey()),
                    webInputPermissionUpsertInfo.getPermissionLevel()
            );
        }
    }

    @JSONField(name = "project_key")
    @Valid
    private WebInputLongIdKey projectKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    @JSONField(name = "permission_level")
    private int permissionLevel;

    public WebInputPermissionUpsertInfo() {
    }

    public WebInputLongIdKey getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(WebInputLongIdKey projectKey) {
        this.projectKey = projectKey;
    }

    public WebInputStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(WebInputStringIdKey userKey) {
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
        return "WebInputPermissionUpsertInfo{" +
                "projectKey=" + projectKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
