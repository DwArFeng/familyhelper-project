package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.ProjectCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.ProjectUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.handler.ProjectOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.ProjectOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class ProjectOperateServiceImpl implements ProjectOperateService {

    private final ProjectOperateHandler projectOperateHandler;

    private final ServiceExceptionMapper sem;

    public ProjectOperateServiceImpl(ProjectOperateHandler projectOperateHandler, ServiceExceptionMapper sem) {
        this.projectOperateHandler = projectOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createProject(StringIdKey userKey, ProjectCreateInfo projectCreateInfo) throws ServiceException {
        try {
            return projectOperateHandler.createProject(userKey, projectCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("创建工程时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void updateProject(StringIdKey userKey, ProjectUpdateInfo projectUpdateInfo) throws ServiceException {
        try {
            projectOperateHandler.updateProject(userKey, projectUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新工程时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeProject(StringIdKey userKey, LongIdKey projectKey) throws ServiceException {
        try {
            projectOperateHandler.removeProject(userKey, projectKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除工程时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void upsertPermission(StringIdKey ownerUserKey, PermissionUpsertInfo permissionUpsertInfo)
            throws ServiceException {
        try {
            projectOperateHandler.upsertPermission(ownerUserKey, permissionUpsertInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("添加或更新工程的权限时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removePermission(StringIdKey ownerUserKey, PermissionRemoveInfo permissionRemoveInfo)
            throws ServiceException {
        try {
            projectOperateHandler.removePermission(ownerUserKey, permissionRemoveInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("移除工程的权限时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
