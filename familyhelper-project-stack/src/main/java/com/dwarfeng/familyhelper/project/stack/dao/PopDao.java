package com.dwarfeng.familyhelper.project.stack.dao;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 工程摘要数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PopDao extends BatchBaseDao<PopKey, Pop>, EntireLookupDao<Pop>,
        PresetLookupDao<Pop> {
}
