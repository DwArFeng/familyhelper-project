package com.dwarfeng.familyhelper.project.stack.cache;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 备忘录提醒驱动器支持缓存。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindDriverSupportCache extends BatchBaseCache<StringIdKey, MemoRemindDriverSupport> {
}
