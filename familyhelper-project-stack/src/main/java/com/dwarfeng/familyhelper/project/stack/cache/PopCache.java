package com.dwarfeng.familyhelper.project.stack.cache;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 工程摘要缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PopCache extends BatchBaseCache<PopKey, Pop> {
}
