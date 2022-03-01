package com.dwarfeng.familyhelper.project.stack.cache;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 备忘录缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface MemoCache extends BatchBaseCache<LongIdKey, Memo> {
}
