package com.dwarfeng.familyhelper.project.stack.dao;

import com.dwarfeng.familyhelper.project.stack.bean.entity.TimePoint;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 时间点数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TimePointDao extends BatchBaseDao<LongIdKey, TimePoint>, EntireLookupDao<TimePoint>,
        PresetLookupDao<TimePoint> {
}
