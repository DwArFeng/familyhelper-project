package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 备忘录提醒驱动器支持维护服务。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindDriverSupportMaintainService extends BatchCrudService<StringIdKey, MemoRemindDriverSupport>,
        EntireLookupService<MemoRemindDriverSupport>, PresetLookupService<MemoRemindDriverSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";

    /**
     * 重置调度器支持。
     *
     * @throws ServiceException 服务异常。
     */
    void reset() throws ServiceException;
}
