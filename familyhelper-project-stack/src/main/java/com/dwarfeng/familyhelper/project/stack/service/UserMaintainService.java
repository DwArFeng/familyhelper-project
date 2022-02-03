package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;

/**
 * 用户维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface UserMaintainService extends BatchCrudService<StringIdKey, User> {

}
