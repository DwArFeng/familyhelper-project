package com.dwarfeng.familyhelper.project.stack.cache;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 备忘录文件信息缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface MemoFileInfoCache extends BatchBaseCache<LongIdKey, MemoFileInfo> {
}
