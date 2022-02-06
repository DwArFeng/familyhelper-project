package com.dwarfeng.familyhelper.project.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 工程权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonPopKey implements Key {

    private static final long serialVersionUID = 1552463475837308403L;

    public static JSFixedFastJsonPopKey of(PopKey key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            return new JSFixedFastJsonPopKey(key.getLongId(), key.getStringId());
        }
    }

    @JSONField(name = "long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long longId;

    @JSONField(name = "string_id", ordinal = 2)
    private String stringId;

    public JSFixedFastJsonPopKey() {
    }

    public JSFixedFastJsonPopKey(Long longId, String stringId) {
        this.longId = longId;
        this.stringId = stringId;
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonPopKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
