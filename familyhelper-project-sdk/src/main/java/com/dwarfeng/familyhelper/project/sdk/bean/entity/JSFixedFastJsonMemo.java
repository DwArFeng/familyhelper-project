package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 备忘录。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonMemo implements Bean {

    private static final long serialVersionUID = -2696044643523820394L;

    public static JSFixedFastJsonMemo of(Memo memo) {
        if (Objects.isNull(memo)) {
            return null;
        } else {
            return new JSFixedFastJsonMemo(
                    JSFixedFastJsonLongIdKey.of(memo.getKey()),
                    FastJsonStringIdKey.of(memo.getUserKey()),
                    memo.getProfile(), memo.getRemark(), memo.getStatus(), memo.getCreatedDate(),
                    memo.getModifiedDate(), memo.getFinishedDate()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

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

    public JSFixedFastJsonMemo() {
    }

    public JSFixedFastJsonMemo(
            JSFixedFastJsonLongIdKey key, FastJsonStringIdKey userKey, String profile, String remark, int status,
            Date createdDate, Date modifiedDate, Date finishedDate
    ) {
        this.key = key;
        this.userKey = userKey;
        this.profile = profile;
        this.remark = remark;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.finishedDate = finishedDate;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
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

    @Override
    public String toString() {
        return "JSFixedFastJsonMemo{" +
                "key=" + key +
                ", userKey=" + userKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", finishedDate=" + finishedDate +
                '}';
    }
}
