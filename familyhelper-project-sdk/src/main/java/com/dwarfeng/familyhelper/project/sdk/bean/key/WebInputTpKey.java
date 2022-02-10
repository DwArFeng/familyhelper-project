package com.dwarfeng.familyhelper.project.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * WebInput 任务对主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputTpKey implements Key {

    private static final long serialVersionUID = -1355957193919834961L;

    public static TpKey toStackBean(WebInputTpKey webInputTpKey) {
        if (Objects.isNull(webInputTpKey)) {
            return null;
        } else {
            return new TpKey(
                    webInputTpKey.getLeftTaskId(), webInputTpKey.getRightTaskId()
            );
        }
    }

    @JSONField(name = "left_task_id")
    private Long leftTaskId;

    @JSONField(name = "right_task_id")
    private Long rightTaskId;

    public WebInputTpKey() {
    }

    public WebInputTpKey(long leftTaskId, long rightTaskId) {
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

        WebInputTpKey that = (WebInputTpKey) o;

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
        return "WebInputTpKey{" +
                "leftTaskId=" + leftTaskId +
                ", rightTaskId=" + rightTaskId +
                '}';
    }
}
