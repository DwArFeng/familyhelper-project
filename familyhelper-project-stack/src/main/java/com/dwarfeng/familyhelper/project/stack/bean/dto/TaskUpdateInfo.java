package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TaskUpdateInfo implements Dto {

    private static final long serialVersionUID = 2836247040933734330L;

    private LongIdKey taskKey;
    private String type;
    private String name;
    private String description;
    private String remark;
    private int totalMissionCount;
    private int finishedMissionCount;

    public TaskUpdateInfo() {
    }

    public TaskUpdateInfo(
            LongIdKey taskKey, String type, String name, String description, String remark, int totalMissionCount,
            int finishedMissionCount
    ) {
        this.taskKey = taskKey;
        this.type = type;
        this.name = name;
        this.description = description;
        this.remark = remark;
        this.totalMissionCount = totalMissionCount;
        this.finishedMissionCount = finishedMissionCount;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
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

    public int getFinishedMissionCount() {
        return finishedMissionCount;
    }

    public void setFinishedMissionCount(int finishedMissionCount) {
        this.finishedMissionCount = finishedMissionCount;
    }

    @Override
    public String toString() {
        return "TaskUpdateInfo{" +
                "taskKey=" + taskKey +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", remark='" + remark + '\'' +
                ", totalMissionCount=" + totalMissionCount +
                ", finishedMissionCount=" + finishedMissionCount +
                '}';
    }
}
