package com.dwarfeng.familyhelper.project.stack.cache;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 备忘录提醒驱动器信息缓存。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindDriverInfoCache extends BatchBaseCache<LongIdKey, MemoRemindDriverInfo> {
}
