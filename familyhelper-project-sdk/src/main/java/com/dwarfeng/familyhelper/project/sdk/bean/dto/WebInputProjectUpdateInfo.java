package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.dto.ProjectUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 工程更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputProjectUpdateInfo implements Dto {

    private static final long serialVersionUID = -5418902718286616093L;

    public static ProjectUpdateInfo toStackBean(WebInputProjectUpdateInfo webInputProjectUpdateInfo) {
        if (Objects.isNull(webInputProjectUpdateInfo)) {
            return null;
        } else {
            return new ProjectUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputProjectUpdateInfo.getProjectKey()),
                    webInputProjectUpdateInfo.getName(), webInputProjectUpdateInfo.getRemark(),
                    webInputProjectUpdateInfo.getStatus()
            );
        }
    }

    @JSONField(name = "project_key")
    @Valid
    @NotNull
    private WebInputLongIdKey projectKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "status")
    private int status;

    public WebInputProjectUpdateInfo() {
    }

    public WebInputLongIdKey getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(WebInputLongIdKey projectKey) {
        this.projectKey = projectKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WebInputProjectUpdateInfo{" +
                "projectKey=" + projectKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }
}
