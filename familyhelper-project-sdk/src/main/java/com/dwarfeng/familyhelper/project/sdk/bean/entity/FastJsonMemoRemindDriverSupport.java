package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverSupport;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 备忘录提醒驱动器支持。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class FastJsonMemoRemindDriverSupport implements Bean {

    private static final long serialVersionUID = -6384742014729492935L;

    public static FastJsonMemoRemindDriverSupport of(MemoRemindDriverSupport memoRemindDriverSupport) {
        if (Objects.isNull(memoRemindDriverSupport)) {
            return null;
        } else {
            return new FastJsonMemoRemindDriverSupport(
                    FastJsonStringIdKey.of(memoRemindDriverSupport.getKey()),
                    memoRemindDriverSupport.getLabel(), memoRemindDriverSupport.getDescription(),
                    memoRemindDriverSupport.getExampleParam()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "description", ordinal = 3)
    private String description;

    @JSONField(name = "example_param", ordinal = 4)
    private String exampleParam;

    public FastJsonMemoRemindDriverSupport() {
    }

    public FastJsonMemoRemindDriverSupport(
            FastJsonStringIdKey key, String label, String description, String exampleParam
    ) {
        this.key = key;
        this.label = label;
        this.description = description;
        this.exampleParam = exampleParam;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExampleParam() {
        return exampleParam;
    }

    public void setExampleParam(String exampleParam) {
        this.exampleParam = exampleParam;
    }

    @Override
    public String toString() {
        return "FastJsonMemoRemindDriverSupport{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", exampleParam='" + exampleParam + '\'' +
                '}';
    }
}
