package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 时间点更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TimePointUpdateInfo implements Dto {

    private static final long serialVersionUID = -3405754303272124481L;

    private LongIdKey timePointKey;
    private String profile;
    private String remark;
    private Date expectedFinishedDate;

    public TimePointUpdateInfo() {
    }

    public TimePointUpdateInfo(LongIdKey timePointKey, String profile, String remark, Date expectedFinishedDate) {
        this.timePointKey = timePointKey;
        this.profile = profile;
        this.remark = remark;
        this.expectedFinishedDate = expectedFinishedDate;
    }

    public LongIdKey getTimePointKey() {
        return timePointKey;
    }

    public void setTimePointKey(LongIdKey timePointKey) {
        this.timePointKey = timePointKey;
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
        return "TimePointUpdateInfo{" +
                "timePointKey=" + timePointKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", expectedFinishedDate=" + expectedFinishedDate +
                '}';
    }
}
