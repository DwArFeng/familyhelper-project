package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 备忘录维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface MemoMaintainService extends BatchCrudService<LongIdKey, Memo>,
        EntireLookupService<Memo>, PresetLookupService<Memo> {

    String CHILD_FOR_USER = "child_for_user";
    String CHILD_FOR_USER_IN_PROGRESS = "child_for_user_in_progress";
    String CHILD_FOR_USER_CREATED_DATE_DESC = "child_for_user_created_date_desc";
    String CHILD_FOR_USER_IN_PROGRESS_CREATED_DATE_DESC = "child_for_user_in_progress_created_date_desc";
    String CHILD_FOR_USER_FINISHED_CREATED_DATE_DESC = "child_for_user_finished_created_date_desc";
}
