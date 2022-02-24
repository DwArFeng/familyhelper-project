package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.TimePoint;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 时间点维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TimePointMaintainService extends BatchCrudService<LongIdKey, TimePoint>,
        EntireLookupService<TimePoint>, PresetLookupService<TimePoint> {

    String CHILD_FOR_TASK = "child_for_task";
    String CHILD_FOR_TASK_EXPECTED_FINISHED_DATE_ASC = "child_for_task_expected_finished_date_asc";
    String CHILD_FOR_TASK_EXPECTED_FINISHED_DATE_DESC = "child_for_task_expected_finished_date_desc";
    String CHILD_FOR_TASK_ACTUAL_FINISHED_DATE_ASC = "child_for_task_actual_finished_date_asc";
    String CHILD_FOR_TASK_ACTUAL_FINISHED_DATE_DESC = "child_for_task_actual_finished_date_desc";
}
