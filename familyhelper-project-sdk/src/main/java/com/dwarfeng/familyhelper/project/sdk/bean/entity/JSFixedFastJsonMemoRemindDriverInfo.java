package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 备忘录提醒驱动信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class JSFixedFastJsonMemoRemindDriverInfo implements Bean {

    private static final long serialVersionUID = 4922270411296362763L;

    public static JSFixedFastJsonMemoRemindDriverInfo of(MemoRemindDriverInfo memoRemindDriverInfo) {
        if (Objects.isNull(memoRemindDriverInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonMemoRemindDriverInfo(
                    JSFixedFastJsonLongIdKey.of(memoRemindDriverInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(memoRemindDriverInfo.getMemoKey()),
                    memoRemindDriverInfo.isEnabled(), memoRemindDriverInfo.getMessage(), memoRemindDriverInfo.getType(),
                    memoRemindDriverInfo.getParam(), memoRemindDriverInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "memo_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey memoKey;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "message", ordinal = 4)
    private String message;

    @JSONField(name = "type", ordinal = 5)
    private String type;

    @JSONField(name = "param", ordinal = 6)
    private String param;

    @JSONField(name = "remark", ordinal = 7)
    private String remark;

    public JSFixedFastJsonMemoRemindDriverInfo() {
    }

    public JSFixedFastJsonMemoRemindDriverInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey memoKey, boolean enabled, String message,
            String type, String param, String remark
    ) {
        this.key = key;
        this.memoKey = memoKey;
        this.enabled = enabled;
        this.message = message;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getMemoKey() {
        return memoKey;
    }

    public void setMemoKey(JSFixedFastJsonLongIdKey memoKey) {
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
        return "JSFixedFastJsonMemoRemindDriverInfo{" +
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
