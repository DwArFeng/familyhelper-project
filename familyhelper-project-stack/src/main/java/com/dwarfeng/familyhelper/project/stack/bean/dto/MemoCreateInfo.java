package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 备忘录创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class MemoCreateInfo implements Dto {

    private static final long serialVersionUID = 7738092515057426595L;

    private StringIdKey userKey;
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

    public MemoCreateInfo() {
    }

    public MemoCreateInfo(StringIdKey userKey, String profile, String remark, boolean starFlag, int priority) {
        this.userKey = userKey;
        this.profile = profile;
        this.remark = remark;
        this.starFlag = starFlag;
        this.priority = priority;
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
        return "MemoCreateInfo{" +
                "userKey=" + userKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                ", starFlag=" + starFlag +
                ", priority=" + priority +
                '}';
    }
}
