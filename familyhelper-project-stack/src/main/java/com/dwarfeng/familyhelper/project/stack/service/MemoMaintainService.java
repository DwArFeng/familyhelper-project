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
}
