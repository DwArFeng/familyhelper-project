package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PermissionRemoveInfo;
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
public class WebInputPermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = 486847333677971303L;

    public static PermissionRemoveInfo toStackBean(WebInputPermissionRemoveInfo webInputPermissionRemoveInfo) {
        if (Objects.isNull(webInputPermissionRemoveInfo)) {
            return null;
        } else {
            return new PermissionRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInputPermissionRemoveInfo.getProjectKey()),
                    WebInputStringIdKey.toStackBean(webInputPermissionRemoveInfo.getUserKey())
            );
        }
    }

    @JSONField(name = "project_key")
    @Valid
    private WebInputLongIdKey projectKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    public WebInputPermissionRemoveInfo() {
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

    @Override
    public String toString() {
        return "WebInputPermissionRemoveInfo{" +
                "projectKey=" + projectKey +
                ", userKey=" + userKey +
                '}';
    }
}
