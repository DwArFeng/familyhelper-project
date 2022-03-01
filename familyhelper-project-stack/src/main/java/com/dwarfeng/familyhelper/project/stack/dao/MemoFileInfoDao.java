package com.dwarfeng.familyhelper.project.stack.dao;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 备忘录文件信息数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface MemoFileInfoDao extends BatchBaseDao<LongIdKey, MemoFileInfo>, EntireLookupDao<MemoFileInfo>,
        PresetLookupDao<MemoFileInfo> {
}
