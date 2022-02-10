package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 任务维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TaskMaintainService extends BatchCrudService<LongIdKey, Task>,
        EntireLookupService<Task>, PresetLookupService<Task> {

    String CHILD_FOR_PROJECT = "child_for_project";
    String PRE_TASK_FOR = "pre_task_for";
    String NAME_LIKE = "name_like";
}
