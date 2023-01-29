package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 备忘录更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class MemoUpdateInfo implements Dto {

    private static final long serialVersionUID = 6854102238103663368L;
    
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

    public MemoUpdateInfo() {
    }

    public MemoUpdateInfo(LongIdKey memoKey, String profile, String remark, boolean starFlag, int priority) {
        this.memoKey = memoKey;
        this.profile = profile;
        this.remark = remark;
        this.starFlag = starFlag;
        this.priority = priority;
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

    @Override
    public String toString() {
        return "MemoUpdateInfo{" +
                "memoKey=" + memoKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", starFlag=" + starFlag +
                ", priority=" + priority +
                '}';
    }
}
