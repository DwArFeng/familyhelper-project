package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoCreateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 备忘录创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputMemoCreateInfo implements Dto {

    private static final long serialVersionUID = 234489482855578536L;

    public static MemoCreateInfo toStackBean(WebInputMemoCreateInfo webInputMemoCreateInfo) {
        if (Objects.isNull(webInputMemoCreateInfo)) {
            return null;
        } else {
            return new MemoCreateInfo(
                    WebInputStringIdKey.toStackBean(webInputMemoCreateInfo.getUserKey()),
                    webInputMemoCreateInfo.getProfile(), webInputMemoCreateInfo.getRemark(),
                    webInputMemoCreateInfo.isStarFlag(), webInputMemoCreateInfo.getPriority(),
                    webInputMemoCreateInfo.getExpectedFinishDate()
            );
        }
    }

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    @JSONField(name = "profile")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_PROFILE)
    private String profile;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "star_flag")
    private boolean starFlag;

    @JSONField(name = "priority")
    @PositiveOrZero
    private int priority;

    @JSONField(name = "expected_finish_date")
    private Date expectedFinishDate;

    public WebInputMemoCreateInfo() {
    }

    public WebInputStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(WebInputStringIdKey userKey) {
        this.userKey = userKey;
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

    public boolean isStarFlag() {
        return starFlag;
    }

    public void setStarFlag(boolean starFlag) {
        this.starFlag = starFlag;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getExpectedFinishDate() {
        return expectedFinishDate;
    }

    public void setExpectedFinishDate(Date expectedFinishDate) {
        this.expectedFinishDate = expectedFinishDate;
    }

    @Override
    public String toString() {
        return "WebInputMemoCreateInfo{" +
                "userKey=" + userKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", starFlag=" + starFlag +
                ", priority=" + priority +
                ", expectedFinishDate=" + expectedFinishDate +
                '}';
    }
}
