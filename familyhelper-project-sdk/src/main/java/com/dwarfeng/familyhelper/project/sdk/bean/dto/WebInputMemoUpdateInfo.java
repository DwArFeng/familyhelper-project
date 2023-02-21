package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.Objects;

/**
 * 备忘录更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputMemoUpdateInfo implements Dto {

    private static final long serialVersionUID = -6305723953849456377L;

    public static MemoUpdateInfo toStackBean(WebInputMemoUpdateInfo webInputMemoUpdateInfo) {
        if (Objects.isNull(webInputMemoUpdateInfo)) {
            return null;
        } else {
            return new MemoUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputMemoUpdateInfo.getMemoKey()),
                    webInputMemoUpdateInfo.getProfile(), webInputMemoUpdateInfo.getRemark(),
                    webInputMemoUpdateInfo.isStarFlag(), webInputMemoUpdateInfo.getPriority(),
                    webInputMemoUpdateInfo.getExpectedFinishDate()
            );
        }
    }

    @JSONField(name = "memo_key")
    @Valid
    private WebInputLongIdKey memoKey;

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

    public WebInputMemoUpdateInfo() {
    }

    public WebInputLongIdKey getMemoKey() {
        return memoKey;
    }

    public void setMemoKey(WebInputLongIdKey memoKey) {
        this.memoKey = memoKey;
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
        return "WebInputMemoUpdateInfo{" +
                "memoKey=" + memoKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", starFlag=" + starFlag +
                ", priority=" + priority +
                ", expectedFinishDate=" + expectedFinishDate +
                '}';
    }
}
