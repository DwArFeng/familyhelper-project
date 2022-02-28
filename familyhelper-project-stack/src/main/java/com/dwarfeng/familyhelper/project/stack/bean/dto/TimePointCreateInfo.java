package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 时间点创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TimePointCreateInfo implements Dto {

    private static final long serialVersionUID = 1342940617536233210L;

    private LongIdKey taskKey;
    private String profile;
    private String remark;
    private Date expectedFinishedDate;

    public TimePointCreateInfo() {
    }

    public TimePointCreateInfo(LongIdKey taskKey, String profile, String remark, Date expectedFinishedDate) {
        this.taskKey = taskKey;
        this.profile = profile;
        this.remark = remark;
        this.expectedFinishedDate = expectedFinishedDate;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getExpectedFinishedDate() {
        return expectedFinishedDate;
    }

    public void setExpectedFinishedDate(Date expectedFinishedDate) {
        this.expectedFinishedDate = expectedFinishedDate;
    }

    @Override
    public String toString() {
        return "TimePointCreateInfo{" +
                "taskKey=" + taskKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", expectedFinishedDate=" + expectedFinishedDate +
                '}';
    }
}
