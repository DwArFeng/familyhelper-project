package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 任务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputTask implements Bean {

    private static final long serialVersionUID = 2825182333389493315L;

    public static Task toStackBean(WebInputTask webInputTask) {
        if (Objects.isNull(webInputTask)) {
            return null;
        } else {
            return new Task(
                    WebInputLongIdKey.toStackBean(webInputTask.getKey()),
                    WebInputLongIdKey.toStackBean(webInputTask.getProjectKey()),
                    webInputTask.getType(), webInputTask.getName(), webInputTask.getDescription(),
                    webInputTask.getRemark(), webInputTask.getStatus(), webInputTask.getCreatedDate(),
                    webInputTask.getModifiedDate(), webInputTask.getFinishedDate(),
                    webInputTask.getTotalMissionCount(), webInputTask.getFinishedMissionCount()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

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

    @JSONField(name = "status")
    private int status;

    @JSONField(name = "created_date")
    @Null
    private Date createdDate;

    @JSONField(name = "modified_date")
    @Null
    private Date modifiedDate;

    @JSONField(name = "finished_date")
    @Null
    private Date finishedDate;

    @JSONField(name = "total_mission_count")
    private int totalMissionCount;

    @JSONField(name = "finished_mission_count")
    private int finishedMissionCount;

    public WebInputTask() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public int getTotalMissionCount() {
        return totalMissionCount;
    }

    public void setTotalMissionCount(int totalMissionCount) {
        this.totalMissionCount = totalMissionCount;
    }

    public int getFinishedMissionCount() {
        return finishedMissionCount;
    }

    public void setFinishedMissionCount(int finishedMissionCount) {
        this.finishedMissionCount = finishedMissionCount;
    }

    @Override
    public String toString() {
        return "WebInputTask{" +
                "key=" + key +
                ", projectKey=" + projectKey +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", finishedDate=" + finishedDate +
                ", totalMissionCount=" + totalMissionCount +
                ", finishedMissionCount=" + finishedMissionCount +
                '}';
    }
}
