package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 备忘录提醒驱动信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class WebInputMemoRemindDriverInfo implements Bean {

    private static final long serialVersionUID = 305194315286741344L;

    public static MemoRemindDriverInfo toStackBean(WebInputMemoRemindDriverInfo webInputMemoRemindDriverInfo) {
        if (Objects.isNull(webInputMemoRemindDriverInfo)) {
            return null;
        } else {
            return new MemoRemindDriverInfo(
                    WebInputLongIdKey.toStackBean(webInputMemoRemindDriverInfo.getKey()),
                    WebInputLongIdKey.toStackBean(webInputMemoRemindDriverInfo.getMemoKey()),
                    webInputMemoRemindDriverInfo.isEnabled(), webInputMemoRemindDriverInfo.getMessage(),
                    webInputMemoRemindDriverInfo.getType(), webInputMemoRemindDriverInfo.getParam(),
                    webInputMemoRemindDriverInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "memo_key")
    @Valid
    private WebInputLongIdKey memoKey;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "message")
    @Length(max = Constraints.LENGTH_MESSAGE)
    private String message;

    @JSONField(name = "type")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_TYPE)
    private String type;

    @JSONField(name = "param")
    private String param;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputMemoRemindDriverInfo() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getMemoKey() {
        return memoKey;
    }

    public void setMemoKey(WebInputLongIdKey memoKey) {
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
        return "WebInputMemoRemindDriverInfo{" +
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
