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

    private static final long serialVersionUID = -4299626647989361611L;

    private LongIdKey memoKey;
    private String profile;
    private String remark;

    public MemoUpdateInfo() {
    }

    public MemoUpdateInfo(LongIdKey memoKey, String profile, String remark) {
        this.memoKey = memoKey;
        this.profile = profile;
        this.remark = remark;
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

    @Override
    public String toString() {
        return "MemoUpdateInfo{" +
                "memoKey=" + memoKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
