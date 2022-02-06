package com.dwarfeng.familyhelper.project.stack.cache;

import com.dwarfeng.familyhelper.project.stack.bean.entity.TaskTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 任务类型指示器缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TaskTypeIndicatorCache extends BatchBaseCache<StringIdKey, TaskTypeIndicator> {
}
