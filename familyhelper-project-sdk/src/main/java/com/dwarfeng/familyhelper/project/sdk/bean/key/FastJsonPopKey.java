package com.dwarfeng.familyhelper.project.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 工程摘要主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonPopKey implements Key {

    private static final long serialVersionUID = -191146175558658510L;

    public static FastJsonPopKey of(PopKey key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            return new FastJsonPopKey(key.getLongId(), key.getStringId());
        }
    }

    @JSONField(name = "long_id", ordinal = 1)
    private Long longId;

    @JSONField(name = "string_id", ordinal = 2)
    private String stringId;

    public FastJsonPopKey() {
    }

    public FastJsonPopKey(Long longId, String stringId) {
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
        return "FastJsonPopKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
