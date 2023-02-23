package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 备忘录更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class MemoUpdateInfo implements Dto {

    private static final long serialVersionUID = 3899143420970263166L;

    private LongIdKey memoKey;
    private String profile;
    private String remark;

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

    public MemoUpdateInfo() {
    }

    public MemoUpdateInfo(
            LongIdKey memoKey, String profile, String remark, boolean starFlag, int priority,
            Date expectedFinishDate, String brief
    ) {
        this.memoKey = memoKey;
        this.profile = profile;
        this.remark = remark;
        this.starFlag = starFlag;
        this.priority = priority;
        this.expectedFinishDate = expectedFinishDate;
        this.brief = brief;
    }

    public LongIdKey getMemoKey() {
        return memoKey;
    }

    public void setMemoKey(LongIdKey memoKey) {
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "MemoUpdateInfo{" +
                "memoKey=" + memoKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", starFlag=" + starFlag +
                ", priority=" + priority +
                ", expectedFinishDate=" + expectedFinishDate +
                ", brief='" + brief + '\'' +
                '}';
    }
}
