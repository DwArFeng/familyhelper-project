package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.bean.key.FastJsonTpKey;
import com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 前置任务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonPreTask implements Bean {

    private static final long serialVersionUID = 2085287206375221677L;

    public static FastJsonPreTask of(PreTask preTask) {
        if (Objects.isNull(preTask)) {
            return null;
        } else {
            return new FastJsonPreTask(
                    FastJsonTpKey.of(preTask.getKey()),
                    preTask.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonTpKey key;

    @JSONField(name = "remark", ordinal = 1)
    private String remark;

    public FastJsonPreTask() {
    }

    public FastJsonPreTask(FastJsonTpKey key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public FastJsonTpKey getKey() {
        return key;
    }

    public void setKey(FastJsonTpKey key) {
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
        return "FastJsonPreTask{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
