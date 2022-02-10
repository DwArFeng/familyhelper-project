package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.bean.key.WebInputTpKey;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 前置任务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputPreTask implements Bean {

    private static final long serialVersionUID = 1698297828478814163L;

    public static PreTask toStackBean(WebInputPreTask webInputPreTask) {
        if (Objects.isNull(webInputPreTask)) {
            return null;
        } else {
            return new PreTask(
                    WebInputTpKey.toStackBean(webInputPreTask.getKey()),
                    webInputPreTask.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputTpKey key;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputPreTask() {
    }

    public WebInputTpKey getKey() {
        return key;
    }

    public void setKey(WebInputTpKey key) {
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
        return "WebInputPreTask{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
