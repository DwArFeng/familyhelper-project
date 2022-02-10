package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.bean.key.JSFixedFastJsonTpKey;
import com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 前置任务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonPreTask implements Bean {

    private static final long serialVersionUID = 1714027892480396828L;

    public static JSFixedFastJsonPreTask of(PreTask preTask) {
        if (Objects.isNull(preTask)) {
            return null;
        } else {
            return new JSFixedFastJsonPreTask(
                    JSFixedFastJsonTpKey.of(preTask.getKey()),
                    preTask.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonTpKey key;

    @JSONField(name = "remark", ordinal = 1)
    private String remark;

    public JSFixedFastJsonPreTask() {
    }

    public JSFixedFastJsonPreTask(JSFixedFastJsonTpKey key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public JSFixedFastJsonTpKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonTpKey key) {
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
        return "JSFixedFastJsonPreTask{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
