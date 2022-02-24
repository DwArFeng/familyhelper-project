package com.dwarfeng.familyhelper.project.stack.cache;

import com.dwarfeng.familyhelper.project.stack.bean.entity.TimePoint;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 时间点缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TimePointCache extends BatchBaseCache<LongIdKey, TimePoint> {
}
