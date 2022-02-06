package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.TaskTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;

/**
 * 任务类型指示器维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TaskTypeIndicatorMaintainService extends BatchCrudService<StringIdKey, TaskTypeIndicator>,
        EntireLookupService<TaskTypeIndicator> {
}
