package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.List;

/**
 * 前置任务重置信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class PreTaskResetInfo implements Dto {

    private static final long serialVersionUID = -568293919954324222L;

    private LongIdKey taskKey;
    private List<LongIdKey> preTasks;

    public PreTaskResetInfo() {
    }

    public PreTaskResetInfo(LongIdKey taskKey, List<LongIdKey> preTasks) {
        this.taskKey = taskKey;
        this.preTasks = preTasks;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public List<LongIdKey> getPreTasks() {
        return preTasks;
    }

    public void setPreTasks(List<LongIdKey> preTasks) {
        this.preTasks = preTasks;
    }

    @Override
    public String toString() {
        return "PreTaskResetInfo{" +
                "taskKey=" + taskKey +
                ", preTasks=" + preTasks +
                '}';
    }
}
