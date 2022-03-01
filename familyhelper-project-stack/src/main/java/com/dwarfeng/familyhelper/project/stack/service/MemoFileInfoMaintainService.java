package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 备忘录文件信息维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface MemoFileInfoMaintainService extends BatchCrudService<LongIdKey, MemoFileInfo>,
        EntireLookupService<MemoFileInfo>, PresetLookupService<MemoFileInfo> {

    String CHILD_FOR_MEMO = "child_for_memo";
    String CHILD_FOR_MEMO_ORIGIN_NAME_ASC = "child_for_memo_origin_name_asc";
    String CHILD_FOR_MEMO_ORIGIN_NAME_DESC = "child_for_memo_origin_name_desc";
    String CHILD_FOR_MEMO_CREATED_DATE_ASC = "child_for_memo_created_date_asc";
    String CHILD_FOR_MEMO_CREATED_DATE_DESC = "child_for_memo_created_date_desc";
    String CHILD_FOR_MEMO_MODIFIED_DATE_ASC = "child_for_memo_modified_date_asc";
    String CHILD_FOR_MEMO_MODIFIED_DATE_DESC = "child_for_memo_modified_date_desc";
    String CHILD_FOR_MEMO_INSPECTED_DATE_ASC = "child_for_memo_inspected_date_asc";
    String CHILD_FOR_MEMO_INSPECTED_DATE_DESC = "child_for_memo_inspected_date_desc";
}
