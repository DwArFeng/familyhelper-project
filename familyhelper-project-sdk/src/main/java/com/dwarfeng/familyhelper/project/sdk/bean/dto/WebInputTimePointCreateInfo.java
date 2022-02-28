package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointCreateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 时间点创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputTimePointCreateInfo implements Dto {

    private static final long serialVersionUID = 828535668151543268L;

    public static TimePointCreateInfo toStackBean(WebInputTimePointCreateInfo webInputTimePointCreateInfo) {
        if (Objects.isNull(webInputTimePointCreateInfo)) {
            return null;
        } else {
            return new TimePointCreateInfo(
                    WebInputLongIdKey.toStackBean(webInputTimePointCreateInfo.getTaskKey()),
                    webInputTimePointCreateInfo.getProfile(), webInputTimePointCreateInfo.getRemark(),
                    webInputTimePointCreateInfo.getExpectedFinishedDate()
            );
        }
    }

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

    @JSONField(name = "expected_finished_date")
    @NotNull
    private Date expectedFinishedDate;

    public WebInputTimePointCreateInfo() {
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

    public Date getExpectedFinishedDate() {
        return expectedFinishedDate;
    }

    public void setExpectedFinishedDate(Date expectedFinishedDate) {
        this.expectedFinishedDate = expectedFinishedDate;
    }

    @Override
    public String toString() {
        return "WebInputTimePointCreateInfo{" +
                "taskKey=" + taskKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", expectedFinishedDate=" + expectedFinishedDate +
                '}';
    }
}
