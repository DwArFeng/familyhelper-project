package com.dwarfeng.familyhelper.project.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 任务类型指示器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TaskTypeIndicator implements Entity<StringIdKey> {

    private static final long serialVersionUID = -3106183725215596724L;
    
    private StringIdKey key;
    private String label;
    private String remark;

    public TaskTypeIndicator() {
    }

    public TaskTypeIndicator(StringIdKey key, String label, String remark) {
        this.key = key;
        this.label = label;
        this.remark = remark;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
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
        return "TaskTypeIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
