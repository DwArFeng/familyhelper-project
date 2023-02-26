package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 备忘录提醒驱动器信息维护服务。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindDriverInfoMaintainService extends BatchCrudService<LongIdKey, MemoRemindDriverInfo>,
        EntireLookupService<MemoRemindDriverInfo>, PresetLookupService<MemoRemindDriverInfo> {

    String CHILD_FOR_MEMO = "child_for_memo";
    String ENABLED = "enabled";
    String REGISTRABLE = "registrable";
}
