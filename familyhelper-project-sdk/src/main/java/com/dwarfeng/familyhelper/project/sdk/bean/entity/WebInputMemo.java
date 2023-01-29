package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 备忘录。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputMemo implements Bean {

    private static final long serialVersionUID = -8048667671753652283L;

    public static Memo toStackBean(WebInputMemo webInputMemo) {
        if (Objects.isNull(webInputMemo)) {
            return null;
        } else {
            return new Memo(
                    WebInputLongIdKey.toStackBean(webInputMemo.getKey()),
                    WebInputStringIdKey.toStackBean(webInputMemo.getUserKey()),
                    webInputMemo.getProfile(), webInputMemo.getRemark(), webInputMemo.getStatus(),
                    webInputMemo.getCreatedDate(), webInputMemo.getModifiedDate(), webInputMemo.getFinishedDate(),
                    webInputMemo.isStarFlag(), webInputMemo.getPriority()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

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

    @JSONField(name = "status")
    private int status;

    @JSONField(name = "created_date")
    private Date createdDate;

    @JSONField(name = "modified_date")
    private Date modifiedDate;

    @JSONField(name = "finished_date")
    private Date finishedDate;

    @JSONField(name = "star_flag")
    private boolean starFlag;

    @JSONField(name = "priority")
    @PositiveOrZero
    private int priority;

    public WebInputMemo() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
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

    @Override
    public String toString() {
        return "WebInputMemo{" +
                "key=" + key +
                ", userKey=" + userKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", finishedDate=" + finishedDate +
                ", starFlag=" + starFlag +
                ", priority=" + priority +
                '}';
    }
}
