package com.dwarfeng.familyhelper.project.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 工程摘要主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class PopKey implements Key {

    private static final long serialVersionUID = 3798987067061926666L;

    private Long longId;
    private String stringId;

    public PopKey() {
    }

    public PopKey(Long longId, String stringId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PopKey popKey = (PopKey) o;

        if (!Objects.equals(longId, popKey.longId)) return false;
        return Objects.equals(stringId, popKey.stringId);
    }

    @Override
    public int hashCode() {
        int result = longId != null ? longId.hashCode() : 0;
        result = 31 * result + (stringId != null ? stringId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PopKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
