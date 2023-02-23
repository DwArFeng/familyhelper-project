package com.dwarfeng.familyhelper.project.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.Date;

/**
 * 备忘录。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Memo implements Entity<LongIdKey> {

    private static final long serialVersionUID = -870973508866469460L;

    private LongIdKey key;
    private StringIdKey userKey;
    private String profile;
    private String remark;
    private int status;
    private Date createdDate;
    private Date modifiedDate;
    private Date finishedDate;

    /**
     * @since 1.1.1
     */
    private boolean starFlag;

    /**
     * @since 1.1.1
     */
    private int priority;

    /**
     * @since 1.2.0
     */
    private Date expectedFinishDate;

    /**
     * @since 1.2.0
     */
    private String brief;

    public Memo() {
    }

    public Memo(
            LongIdKey key, StringIdKey userKey, String profile, String remark, int status, Date createdDate,
            Date modifiedDate, Date finishedDate, boolean starFlag, int priority, Date expectedFinishDate, String brief
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
        this.expectedFinishDate = expectedFinishDate;
        this.brief = brief;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
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

    public Date getExpectedFinishDate() {
        return expectedFinishDate;
    }

    public void setExpectedFinishDate(Date expectedFinishDate) {
        this.expectedFinishDate = expectedFinishDate;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "Memo{" +
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
                ", expectedFinishDate=" + expectedFinishDate +
                ", brief='" + brief + '\'' +
                '}';
    }
}
