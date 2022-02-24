package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.entity.TimePoint;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.Objects;

/**
 * FastJson 时间点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputTimePoint implements Bean {

    private static final long serialVersionUID = 6690031412114917024L;

    public static TimePoint toStackBean(WebInputTimePoint webInputTimePoint) {
        if (Objects.isNull(webInputTimePoint)) {
            return null;
        } else {
            return new TimePoint(
                    WebInputLongIdKey.toStackBean(webInputTimePoint.getKey()),
                    WebInputLongIdKey.toStackBean(webInputTimePoint.getTaskKey()),
                    webInputTimePoint.getProfile(), webInputTimePoint.getRemark(), webInputTimePoint.getStatus(),
                    webInputTimePoint.getExpectedFinishedDate(), webInputTimePoint.getActualFinishedDate()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "task_key")
    @Valid
    private WebInputLongIdKey taskKey;

    @JSONField(name = "profile")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_PROFILE)
    private String profile;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "status")
    private int status;

    @JSONField(name = "expected_finished_date")
    private Date expectedFinishedDate;

    @JSONField(name = "actual_finished_date")
    @Null
    private Date actualFinishedDate;

    public WebInputTimePoint() {
    }

    public WebInputTimePoint(
            WebInputLongIdKey key, WebInputLongIdKey taskKey, String profile, String remark, int status,
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

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
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
        return "WebInputTimePoint{" +
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
