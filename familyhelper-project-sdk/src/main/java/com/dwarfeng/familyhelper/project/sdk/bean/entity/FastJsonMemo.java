package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 备忘录。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonMemo implements Bean {

    private static final long serialVersionUID = -6416009986821564089L;

    public static FastJsonMemo of(Memo memo) {
        if (Objects.isNull(memo)) {
            return null;
        } else {
            return new FastJsonMemo(
                    FastJsonLongIdKey.of(memo.getKey()),
                    FastJsonStringIdKey.of(memo.getUserKey()),
                    memo.getProfile(), memo.getRemark(), memo.getStatus(), memo.getCreatedDate(),
                    memo.getModifiedDate(), memo.getFinishedDate(), memo.isStarFlag(), memo.getPriority()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "user_key", ordinal = 2)
    private FastJsonStringIdKey userKey;

    @JSONField(name = "profile", ordinal = 3)
    private String profile;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    @JSONField(name = "status", ordinal = 5)
    private int status;

    @JSONField(name = "created_date", ordinal = 6)
    private Date createdDate;

    @JSONField(name = "modified_date", ordinal = 7)
    private Date modifiedDate;

    @JSONField(name = "finished_date", ordinal = 8)
    private Date finishedDate;

    @JSONField(name = "star_flag", ordinal = 9)
    private boolean starFlag;

    @JSONField(name = "priority", ordinal = 10)
    private int priority;

    public FastJsonMemo() {
    }

    public FastJsonMemo(
            FastJsonLongIdKey key, FastJsonStringIdKey userKey, String profile, String remark, int status,
            Date createdDate, Date modifiedDate, Date finishedDate, boolean starFlag, int priority
    ) {
        this.key = key;
        this.userKey = userKey;
        this.profile = profile;
        this.remark = remark;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.finishedDate = finishedDate;
        this.starFlag = starFlag;
        this.priority = priority;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(FastJsonStringIdKey userKey) {
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
        return "FastJsonMemo{" +
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
