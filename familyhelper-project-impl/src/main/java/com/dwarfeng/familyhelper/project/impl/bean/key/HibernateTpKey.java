package com.dwarfeng.familyhelper.project.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.Bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Hibernate 任务对主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class HibernateTpKey implements Bean, Serializable {

    private static final long serialVersionUID = 3214561039294239430L;

    private Long leftTaskId;
    private Long rightTaskId;

    public HibernateTpKey() {
    }

    public HibernateTpKey(long leftTaskId, long rightTaskId) {
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

        HibernateTpKey that = (HibernateTpKey) o;

        if (!Objects.equals(leftTaskId, that.leftTaskId)) return false;
        return Objects.equals(rightTaskId, that.rightTaskId);
    }

    @Override
    public int hashCode() {
        int result = leftTaskId != null ? leftTaskId.hashCode() : 0;
        result = 31 * result + (rightTaskId != null ? rightTaskId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateTpKey{" +
                "leftTaskId=" + leftTaskId +
                ", rightTaskId=" + rightTaskId +
                '}';
    }
}
