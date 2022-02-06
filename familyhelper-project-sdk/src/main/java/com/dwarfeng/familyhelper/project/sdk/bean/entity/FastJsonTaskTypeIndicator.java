package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.entity.TaskTypeIndicator;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 任务类型指示器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonTaskTypeIndicator implements Bean {

    private static final long serialVersionUID = -1120403936556697567L;

    public static FastJsonTaskTypeIndicator of(TaskTypeIndicator TaskTypeIndicator) {
        if (Objects.isNull(TaskTypeIndicator)) {
            return null;
        } else {
            return new FastJsonTaskTypeIndicator(
                    FastJsonStringIdKey.of(TaskTypeIndicator.getKey()),
                    TaskTypeIndicator.getLabel(),
                    TaskTypeIndicator.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonTaskTypeIndicator() {
    }

    public FastJsonTaskTypeIndicator(FastJsonStringIdKey key, String label, String remark) {
        this.key = key;
        this.label = label;
        this.remark = remark;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonTaskTypeIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
