package com.dwarfeng.familyhelper.project.stack.dao;

import com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 前置任务数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PreTaskDao extends BatchBaseDao<TpKey, PreTask>, EntireLookupDao<PreTask>,
        PresetLookupDao<PreTask> {
}
