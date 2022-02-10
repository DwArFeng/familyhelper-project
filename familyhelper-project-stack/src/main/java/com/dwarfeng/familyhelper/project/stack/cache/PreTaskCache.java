package com.dwarfeng.familyhelper.project.stack.cache;

import com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 前置任务缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PreTaskCache extends BatchBaseCache<TpKey, PreTask> {
}
