package com.dwarfeng.familyhelper.project.stack.dao;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 备忘录提醒驱动器信息数据访问层。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindDriverInfoDao extends BatchBaseDao<LongIdKey, MemoRemindDriverInfo>,
        EntireLookupDao<MemoRemindDriverInfo>, PresetLookupDao<MemoRemindDriverInfo> {
}
