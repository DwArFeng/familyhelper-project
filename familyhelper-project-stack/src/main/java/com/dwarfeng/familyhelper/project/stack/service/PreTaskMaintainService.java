package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 前置任务维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PreTaskMaintainService extends BatchCrudService<TpKey, PreTask>,
        EntireLookupService<PreTask>, PresetLookupService<PreTask> {

    String CHILD_FOR_SUBJECT_TASK = "child_for_subject_task";
    String CHILD_FOR_OBJECT_TASK = "child_for_object_task";
}
