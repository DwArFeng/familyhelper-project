package com.dwarfeng.familyhelper.project.stack.dao;

import com.dwarfeng.familyhelper.project.stack.bean.entity.TaskTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;

/**
 * 任务类型指示器数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TaskTypeIndicatorDao extends BatchBaseDao<StringIdKey, TaskTypeIndicator>,
        EntireLookupDao<TaskTypeIndicator> {
}
