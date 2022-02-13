package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TaskCreateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.checkerframework.checker.index.qual.Positive;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 任务创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputTaskCreateInfo implements Dto {

    private static final long serialVersionUID = -9050655870152235442L;

    public static TaskCreateInfo toStackBean(WebInputTaskCreateInfo webInputTaskCreateInfo) {
        if (Objects.isNull(webInputTaskCreateInfo)) {
            return null;
        } else {
            return new TaskCreateInfo(
                    WebInputLongIdKey.toStackBean(webInputTaskCreateInfo.getProjectKey()),
                    webInputTaskCreateInfo.getType(),
                    webInputTaskCreateInfo.getName(),
                    webInputTaskCreateInfo.getDescription(),
                    webInputTaskCreateInfo.getRemark(),
                    webInputTaskCreateInfo.getTotalMissionCount()
            );
        }
    }

    @JSONField(name = "project_key")
    @Valid
    private WebInputLongIdKey projectKey;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "name")
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "description")
    private String description;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "total_mission_count")
    @Positive
    private int totalMissionCount;

    public WebInputTaskCreateInfo() {
    }

    public WebInputLongIdKey getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(WebInputLongIdKey projectKey) {
        this.projectKey = projectKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTotalMissionCount() {
        return totalMissionCount;
    }

    public void setTotalMissionCount(int totalMissionCount) {
        this.totalMissionCount = totalMissionCount;
    }

    @Override
    public String toString() {
        return "WebInputTaskCreateInfo{" +
                "projectKey=" + projectKey +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", remark='" + remark + '\'' +
                ", totalMissionCount=" + totalMissionCount +
                '}';
    }
}
