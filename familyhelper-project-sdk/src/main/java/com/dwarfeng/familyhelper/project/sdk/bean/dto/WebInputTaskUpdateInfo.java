package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TaskUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

/**
 * 任务更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputTaskUpdateInfo implements Dto {

    private static final long serialVersionUID = -8719923706441253773L;

    public static TaskUpdateInfo toStackBean(WebInputTaskUpdateInfo webInputTaskUpdateInfo) {
        if (Objects.isNull(webInputTaskUpdateInfo)) {
            return null;
        } else {
            return new TaskUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputTaskUpdateInfo.getTaskKey()),
                    webInputTaskUpdateInfo.getType(),
                    webInputTaskUpdateInfo.getName(),
                    webInputTaskUpdateInfo.getRemark(),
                    webInputTaskUpdateInfo.getTotalMissionCount(),
                    webInputTaskUpdateInfo.getFinishedMissionCount()
            );
        }
    }

    @JSONField(name = "task_key")
    @Valid
    private WebInputLongIdKey taskKey;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "name")
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "total_mission_count")
    @Positive
    private int totalMissionCount;

    @JSONField(name = "finished_mission_count")
    @PositiveOrZero
    private int finishedMissionCount;

    public WebInputTaskUpdateInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
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
        return "WebInputTaskUpdateInfo{" +
                "taskKey=" + taskKey +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", totalMissionCount=" + totalMissionCount +
                ", finishedMissionCount=" + finishedMissionCount +
                '}';
    }
}
