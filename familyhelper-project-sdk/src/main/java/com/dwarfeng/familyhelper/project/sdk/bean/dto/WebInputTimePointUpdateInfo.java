package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 时间点更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputTimePointUpdateInfo implements Dto {

    private static final long serialVersionUID = -5832574906520784971L;

    public static TimePointUpdateInfo toStackBean(WebInputTimePointUpdateInfo webInputTimePointUpdateInfo) {
        if (Objects.isNull(webInputTimePointUpdateInfo)) {
            return null;
        } else {
            return new TimePointUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputTimePointUpdateInfo.getTimePointKey()),
                    webInputTimePointUpdateInfo.getProfile(), webInputTimePointUpdateInfo.getRemark(),
                    webInputTimePointUpdateInfo.getExpectedFinishedDate()
            );
        }
    }

    @JSONField(name = "time_point_key")
    @Valid
    private WebInputLongIdKey timePointKey;

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

    public WebInputTimePointUpdateInfo() {
    }

    public WebInputLongIdKey getTimePointKey() {
        return timePointKey;
    }

    public void setTimePointKey(WebInputLongIdKey timePointKey) {
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
        return "WebInputTimePointUpdateInfo{" +
                "timePointKey=" + timePointKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", expectedFinishedDate=" + expectedFinishedDate +
                '}';
    }
}
