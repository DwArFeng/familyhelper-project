package com.dwarfeng.familyhelper.project.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 任务对主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TpKey implements Key {

    private static final long serialVersionUID = -7494962423533024473L;

    private Long leftTaskId;
    private Long rightTaskId;

    public TpKey() {
    }

    public TpKey(long leftTaskId, long rightTaskId) {
        this.leftTaskId = leftTaskId;
        this.rightTaskId = rightTaskId;
    }

    public Long getLeftTaskId() {
        return leftTaskId;
    }

    public void setLeftTaskId(Long leftTaskId) {
        this.leftTaskId = leftTaskId;
    }

    public Long getRightTaskId() {
        return rightTaskId;
    }

    public void setRightTaskId(Long rightTaskId) {
        this.rightTaskId = rightTaskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TpKey tpKey = (TpKey) o;

        if (!Objects.equals(leftTaskId, tpKey.leftTaskId)) return false;
        return Objects.equals(rightTaskId, tpKey.rightTaskId);
    }

    @Override
    public int hashCode() {
        int result = leftTaskId != null ? leftTaskId.hashCode() : 0;
        result = 31 * result + (rightTaskId != null ? rightTaskId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TpKey{" +
                "leftTaskId=" + leftTaskId +
                ", rightTaskId=" + rightTaskId +
                '}';
    }
}
