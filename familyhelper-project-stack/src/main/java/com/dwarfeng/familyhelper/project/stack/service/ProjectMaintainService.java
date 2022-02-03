package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 工程维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ProjectMaintainService extends BatchCrudService<LongIdKey, Project>,
        EntireLookupService<Project>, PresetLookupService<Project> {

    String NAME_LIKE = "name_like";
}
