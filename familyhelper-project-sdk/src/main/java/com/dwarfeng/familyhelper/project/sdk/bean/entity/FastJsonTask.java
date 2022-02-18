package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 任务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonTask implements Bean {

    private static final long serialVersionUID = 7415746480722937130L;

    public static FastJsonTask of(Task task) {
        if (Objects.isNull(task)) {
            return null;
        } else {
            return new FastJsonTask(
                    FastJsonLongIdKey.of(task.getKey()),
                    FastJsonLongIdKey.of(task.getProjectKey()),
                    task.getType(), task.getName(), task.getRemark(), task.getStatus(),
                    task.getCreatedDate(), task.getModifiedDate(), task.getFinishedDate(), task.getTotalMissionCount(),
                    task.getFinishedMissionCount()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "project_key", ordinal = 2)
    private FastJsonLongIdKey projectKey;

    @JSONField(name = "type", ordinal = 3)
    private String type;

    @JSONField(name = "name", ordinal = 4)
    private String name;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    @JSONField(name = "status", ordinal = 6)
    private int status;

    @JSONField(name = "created_date", ordinal = 7)
    private Date createdDate;

    @JSONField(name = "modified_date", ordinal = 8)
    private Date modifiedDate;

    @JSONField(name = "finished_date", ordinal = 9)
    private Date finishedDate;

    @JSONField(name = "total_mission_count", ordinal = 10)
    private int totalMissionCount;

    @JSONField(name = "finished_mission_count", ordinal = 11)
    private int finishedMissionCount;

    public FastJsonTask() {
    }

    public FastJsonTask(
            FastJsonLongIdKey key, FastJsonLongIdKey projectKey, String type, String name,
            String remark, int status, Date createdDate, Date modifiedDate, Date finishedDate, int totalMissionCount,
            int finishedMissionCount
    ) {
        this.key = key;
        this.projectKey = projectKey;
        this.type = type;
        this.name = name;
        this.remark = remark;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.finishedDate = finishedDate;
        this.totalMissionCount = totalMissionCount;
        this.finishedMissionCount = finishedMissionCount;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(FastJsonLongIdKey projectKey) {
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
        return "FastJsonTask{" +
                "key=" + key +
                ", projectKey=" + projectKey +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
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
