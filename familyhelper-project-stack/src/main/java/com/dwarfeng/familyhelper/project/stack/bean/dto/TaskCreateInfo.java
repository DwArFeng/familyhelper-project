package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TaskCreateInfo implements Dto {

    private static final long serialVersionUID = 7360406093563589882L;

    private LongIdKey projectKey;
    private String type;
    private String name;
    private String remark;
    private int totalMissionCount;

    public TaskCreateInfo() {
    }

    public TaskCreateInfo(
            LongIdKey projectKey, String type, String name, String remark, int totalMissionCount
    ) {
        this.projectKey = projectKey;
        this.type = type;
        this.name = name;
        this.remark = remark;
        this.totalMissionCount = totalMissionCount;
    }

    public LongIdKey getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(LongIdKey projectKey) {
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

    public int getTotalMissionCount() {
        return totalMissionCount;
    }

    public void setTotalMissionCount(int totalMissionCount) {
        this.totalMissionCount = totalMissionCount;
    }

    @Override
    public String toString() {
        return "TaskCreateInfo{" +
                "projectKey=" + projectKey +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", totalMissionCount=" + totalMissionCount +
                '}';
    }
}
