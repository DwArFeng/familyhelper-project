package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 资产目录维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PopMaintainService extends BatchCrudService<PopKey, Pop>,
        EntireLookupService<Pop>, PresetLookupService<Pop> {

    String CHILD_FOR_PROJECT = "child_for_project";
    String CHILD_FOR_USER = "child_for_user";
    String CHILD_FOR_PROJECT_PERMISSION_LEVEL_EQUALS = "child_for_project_permission_level_equals";
    String CHILD_FOR_USER_PERMISSION_LEVEL_EQUALS = "child_for_user_permission_level_equals";
}
