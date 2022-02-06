package com.dwarfeng.familyhelper.project.stack.handler;

import com.dwarfeng.familyhelper.project.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.ProjectCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.ProjectUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 工程操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ProjectOperateHandler extends Handler {

    /**
     * 创建工程。
     *
     * @param userKey           工程的所有者的主键。
     * @param projectCreateInfo 工程的创建信息。
     * @return 生成的工程的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createProject(StringIdKey userKey, ProjectCreateInfo projectCreateInfo)
            throws HandlerException;

    /**
     * 更新工程。
     *
     * @param userKey           工程的所有者的主键。
     * @param projectUpdateInfo 工程的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateProject(StringIdKey userKey, ProjectUpdateInfo projectUpdateInfo)
            throws HandlerException;

    /**
     * 删除工程。
     *
     * @param userKey    工程的所有者的主键。
     * @param projectKey 工程的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeProject(StringIdKey userKey, LongIdKey projectKey) throws HandlerException;

    /**
     * 添加或更新工程的权限。
     *
     * @param ownerUserKey         操作者的主键。
     * @param permissionUpsertInfo 权限添加信息。
     * @throws HandlerException 处理器异常。
     */
    void upsertPermission(StringIdKey ownerUserKey, PermissionUpsertInfo permissionUpsertInfo) throws HandlerException;

    /**
     * 移除工程的权限。
     *
     * @param ownerUserKey         操作者的主键。
     * @param permissionRemoveInfo 权限移除信息。
     * @throws HandlerException 处理器异常。
     */
    void removePermission(StringIdKey ownerUserKey, PermissionRemoveInfo permissionRemoveInfo) throws HandlerException;
}
