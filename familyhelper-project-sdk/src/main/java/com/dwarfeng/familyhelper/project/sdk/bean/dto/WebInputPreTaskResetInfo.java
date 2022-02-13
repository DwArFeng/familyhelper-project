package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PreTaskResetInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * WebInput 前置任务重置信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputPreTaskResetInfo implements Dto {

    private static final long serialVersionUID = 8357495323536073043L;

    public static PreTaskResetInfo toStackBean(WebInputPreTaskResetInfo webInputPreTaskResetInfo) {
        if (Objects.isNull(webInputPreTaskResetInfo)) {
            return null;
        } else {
            return new PreTaskResetInfo(
                    WebInputLongIdKey.toStackBean(webInputPreTaskResetInfo.getTaskKey()),
                    webInputPreTaskResetInfo.getPreTaskKeys().stream().map(WebInputLongIdKey::toStackBean)
                            .collect(Collectors.toList())

            );
        }
    }

    @JSONField(name = "taskKey")
    @Valid
    private WebInputLongIdKey taskKey;

    @JSONField(name = "pre_tasks")
    @NotNull
    @Valid
    private List<WebInputLongIdKey> preTaskKeys;

    public WebInputPreTaskResetInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public List<WebInputLongIdKey> getPreTaskKeys() {
        return preTaskKeys;
    }

    public void setPreTaskKeys(List<WebInputLongIdKey> preTaskKeys) {
        this.preTaskKeys = preTaskKeys;
    }

    @Override
    public String toString() {
        return "WebInputPreTaskResetInfo{" +
                "taskKey=" + taskKey +
                ", preTaskKeys=" + preTaskKeys +
                '}';
    }
}
