package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.entity.TimePoint;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 时间点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonTimePoint implements Bean {

    private static final long serialVersionUID = -8081137172143591010L;

    public static FastJsonTimePoint of(TimePoint timePoint) {
        if (Objects.isNull(timePoint)) {
            return null;
        } else {
            return new FastJsonTimePoint(
                    FastJsonLongIdKey.of(timePoint.getKey()),
                    FastJsonLongIdKey.of(timePoint.getTaskKey()),
                    timePoint.getProfile(), timePoint.getRemark(), timePoint.getStatus(),
                    timePoint.getExpectedFinishedDate(), timePoint.getActualFinishedDate()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "task_key", ordinal = 2)
    private FastJsonLongIdKey taskKey;

    @JSONField(name = "name", ordinal = 3)
    private String profile;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    @JSONField(name = "status", ordinal = 5)
    private int status;

    @JSONField(name = "expected_finished_date", ordinal = 6)
    private Date expectedFinishedDate;

    @JSONField(name = "actual_finished_date", ordinal = 7)
    private Date actualFinishedDate;

    public FastJsonTimePoint() {
    }

    public FastJsonTimePoint(
            FastJsonLongIdKey key, FastJsonLongIdKey taskKey, String profile, String remark, int status,
            Date expectedFinishedDate, Date actualFinishedDate
    ) {
        this.key = key;
        this.taskKey = taskKey;
        this.profile = profile;
        this.remark = remark;
        this.status = status;
        this.expectedFinishedDate = expectedFinishedDate;
        this.actualFinishedDate = actualFinishedDate;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(FastJsonLongIdKey taskKey) {
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
        return "FastJsonTimePoint{" +
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
