package com.dwarfeng.familyhelper.project.stack.cache;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 工程缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ProjectCache extends BatchBaseCache<LongIdKey, Project> {
}
