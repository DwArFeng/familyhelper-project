package com.dwarfeng.familyhelper.project.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 备忘录提醒驱动信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class MemoRemindDriverInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = 7383620968727586357L;

    private LongIdKey key;
    private LongIdKey memoKey;
    private boolean enabled;
    private String message;
    private String type;
    private String param;
    private String remark;

    public MemoRemindDriverInfo() {
    }

    public MemoRemindDriverInfo(
            LongIdKey key, LongIdKey memoKey, boolean enabled, String message, String type, String param,
            String remark
    ) {
        this.key = key;
        this.memoKey = memoKey;
        this.enabled = enabled;
        this.message = message;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getMemoKey() {
        return memoKey;
    }

    public void setMemoKey(LongIdKey memoKey) {
        this.memoKey = memoKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MemoRemindDriverInfo{" +
                "key=" + key +
                ", memoKey=" + memoKey +
                ", enabled=" + enabled +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
