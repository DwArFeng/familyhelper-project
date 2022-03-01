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

    private static final long serialVersionUID = -6485721776134456265L;

    private StringIdKey userKey;
    private String profile;
    private String remark;

    public MemoCreateInfo() {
    }

    public MemoCreateInfo(StringIdKey userKey, String profile, String remark) {
        this.userKey = userKey;
        this.profile = profile;
        this.remark = remark;
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

    @Override
    public String toString() {
        return "MemoCreateInfo{" +
                "userKey=" + userKey +
                ", profile='" + profile + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
