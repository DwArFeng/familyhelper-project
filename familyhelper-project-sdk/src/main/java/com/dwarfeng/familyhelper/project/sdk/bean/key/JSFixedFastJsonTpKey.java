package com.dwarfeng.familyhelper.project.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 任务对主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonTpKey implements Key {

    private static final long serialVersionUID = 4164905921347512259L;

    public static JSFixedFastJsonTpKey of(TpKey tpKey) {
        if (Objects.isNull(tpKey)) {
            return null;
        } else {
            return new JSFixedFastJsonTpKey(
                    tpKey.getLeftTaskId(), tpKey.getRightTaskId()
            );
        }
    }

    @JSONField(name = "left_task_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long leftTaskId;

    @JSONField(name = "right_task_id", ordinal = 2, serializeUsing = ToStringSerializer.class)
    private Long rightTaskId;

    public JSFixedFastJsonTpKey() {
    }

    public JSFixedFastJsonTpKey(long leftTaskId, long rightTaskId) {
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

        JSFixedFastJsonTpKey that = (JSFixedFastJsonTpKey) o;

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
        return "JSFixedFastJsonTpKey{" +
                "leftTaskId=" + leftTaskId +
                ", rightTaskId=" + rightTaskId +
                '}';
    }
}
