package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 备忘录提醒驱动信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class FastJsonMemoRemindDriverInfo implements Bean {

    private static final long serialVersionUID = 7232670702962595276L;

    public static FastJsonMemoRemindDriverInfo of(MemoRemindDriverInfo memoRemindDriverInfo) {
        if (Objects.isNull(memoRemindDriverInfo)) {
            return null;
        } else {
            return new FastJsonMemoRemindDriverInfo(
                    FastJsonLongIdKey.of(memoRemindDriverInfo.getKey()),
                    FastJsonLongIdKey.of(memoRemindDriverInfo.getMemoKey()),
                    memoRemindDriverInfo.isEnabled(), memoRemindDriverInfo.getType(), memoRemindDriverInfo.getParam(),
                    memoRemindDriverInfo.getMessage(), memoRemindDriverInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "memo_key", ordinal = 2)
    private FastJsonLongIdKey memoKey;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "type", ordinal = 4)
    private String type;

    @JSONField(name = "param", ordinal = 5)
    private String param;

    @JSONField(name = "message", ordinal = 6)
    private String message;

    @JSONField(name = "remark", ordinal = 7)
    private String remark;

    public FastJsonMemoRemindDriverInfo() {
    }

    public FastJsonMemoRemindDriverInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey memoKey, boolean enabled, String type, String param,
            String message, String remark
    ) {
        this.key = key;
        this.memoKey = memoKey;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.message = message;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getMemoKey() {
        return memoKey;
    }

    public void setMemoKey(FastJsonLongIdKey memoKey) {
        this.memoKey = memoKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonMemoRemindDriverInfo{" +
                "key=" + key +
                ", memoKey=" + memoKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", message='" + message + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
