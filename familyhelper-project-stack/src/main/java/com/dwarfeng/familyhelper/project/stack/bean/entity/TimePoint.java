package com.dwarfeng.familyhelper.project.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 时间点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TimePoint implements Entity<LongIdKey> {

    private static final long serialVersionUID = 6705657932995671768L;
    
    private LongIdKey key;
    private LongIdKey taskKey;
    private String profile;
    private String remark;
    private int status;
    private Date expectedFinishedDate;
    private Date actualFinishedDate;

    public TimePoint() {
    }

    public TimePoint(
            LongIdKey key, LongIdKey taskKey, String profile, String remark, int status, Date expectedFinishedDate,
            Date actualFinishedDate
    ) {
        this.key = key;
        this.taskKey = taskKey;
        this.profile = profile;
        this.remark = remark;
        this.status = status;
        this.expectedFinishedDate = expectedFinishedDate;
        this.actualFinishedDate = actualFinishedDate;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getExpectedFinishedDate() {
        return expectedFinishedDate;
    }

    public void setExpectedFinishedDate(Date expectedFinishedDate) {
        this.expectedFinishedDate = expectedFinishedDate;
    }

    public Date getActualFinishedDate() {
        return actualFinishedDate;
    }

    public void setActualFinishedDate(Date actualFinishedDate) {
        this.actualFinishedDate = actualFinishedDate;
    }

    @Override
    public String toString() {
        return "TimePoint{" +
                "key=" + key +
                ", taskKey=" + taskKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", expectedFinishedDate=" + expectedFinishedDate +
                ", actualFinishedDate=" + actualFinishedDate +
                '}';
    }
}
