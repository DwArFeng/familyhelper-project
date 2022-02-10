package com.dwarfeng.familyhelper.project.stack.cache;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 任务缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TaskCache extends BatchBaseCache<LongIdKey, Task> {
}
