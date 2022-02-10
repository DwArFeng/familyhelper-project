package com.dwarfeng.familyhelper.project.stack.bean.entity;

import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 前置任务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class PreTask implements Entity<TpKey> {

    private static final long serialVersionUID = 3266737292614441344L;

    private TpKey key;
    private String remark;

    public PreTask() {
    }

    public PreTask(TpKey key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    @Override
    public TpKey getKey() {
        return key;
    }

    @Override
    public void setKey(TpKey key) {
        this.key = key;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "PreTask{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
