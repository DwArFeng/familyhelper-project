package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.ProjectCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.ProjectUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.handler.ProjectOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.PopMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.ProjectMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ProjectOperateHandlerImpl implements ProjectOperateHandler {

    private final ProjectMaintainService projectMaintainService;
    private final PopMaintainService popMaintainService;

    private final HandlerValidator handlerValidator;

    public ProjectOperateHandlerImpl(
            ProjectMaintainService projectMaintainService,
            PopMaintainService popMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.projectMaintainService = projectMaintainService;
        this.popMaintainService = popMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public LongIdKey createProject(StringIdKey userKey, ProjectCreateInfo projectCreateInfo)
            throws HandlerException {
        try {
            int status = projectCreateInfo.getStatus();

            // 1. 确认 status 有效。
            handlerValidator.makeSureProjectStatusValid(status);

            // 2. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 3. 根据 projectCreateInfo 以及创建的规则组合 资产目录 实体。
            Project project = new Project(
                    null, projectCreateInfo.getName(), projectCreateInfo.getRemark(), status, new Date(),
                    new Date(), null
            );

            // 4. 插入资产目录实体，并获取生成的主键。
            LongIdKey projectKey = projectMaintainService.insert(project);

            // 5. 由资产目录实体生成的主键和用户主键组合权限信息，并插入。
            Pop pop = new Pop(
                    new PopKey(projectKey.getLongId(), userKey.getStringId()),
                    Constants.PERMISSION_LEVEL_OWNER,
                    "创建资产目录时自动插入，赋予创建人所有者权限"
            );
            popMaintainService.insert(pop);

            // 6. 返回生成的主键。
            return projectKey;
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateProject(StringIdKey userKey, ProjectUpdateInfo projectUpdateInfo)
            throws HandlerException {
        try {
            LongIdKey projectKey = projectUpdateInfo.getProjectKey();
            int status = projectUpdateInfo.getStatus();

            // 1. 确认 status 有效。
            handlerValidator.makeSureProjectStatusValid(status);

            // 2. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 3. 确认工程存在。
            handlerValidator.makeSureProjectExists(projectKey);

            // 4. 确认用户有权限操作指定的资产目录。
            handlerValidator.makeSureUserPermittedForProject(userKey, projectKey);

            // 5. 根据 projectUpdateInfo 以及更新的规则设置 资产目录 实体。
            Project project = projectMaintainService.get(projectKey);
            project.setName(projectUpdateInfo.getName());
            project.setRemark(projectUpdateInfo.getRemark());

            // 6. 更新资产目录实体。
            projectMaintainService.update(project);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeProject(StringIdKey userKey, LongIdKey projectKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认资产目录存在。
            handlerValidator.makeSureProjectExists(projectKey);

            // 3. 确认用户有权限操作指定的资产目录。
            handlerValidator.makeSureUserPermittedForProject(userKey, projectKey);

            // 4. 删除指定主键的资产目录。
            projectMaintainService.delete(projectKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("ExtractMethodRecommender")
    @Override
    public void upsertPermission(StringIdKey ownerUserKey, PermissionUpsertInfo permissionUpsertInfo)
            throws HandlerException {
        try {
            LongIdKey projectKey = permissionUpsertInfo.getProjectKey();
            StringIdKey targetUserKey = permissionUpsertInfo.getUserKey();
            int permissionLevel = permissionUpsertInfo.getPermissionLevel();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认 permissionLevel 有效。
            handlerValidator.makeSurePermissionLevelValid(permissionLevel);

            // 3. 确认用户存在。
            handlerValidator.makeSureUserExists(ownerUserKey);
            handlerValidator.makeSureUserExists(targetUserKey);

            // 4. 确认资产目录存在。
            handlerValidator.makeSureProjectExists(projectKey);

            // 5. 确认用户有权限操作指定的资产目录。
            handlerValidator.makeSureUserPermittedForProject(ownerUserKey, projectKey);

            // 6. 通过入口信息组合权限实体，并进行插入或更新操作。
            String permissionLabel;
            switch (permissionLevel) {
                case Constants.PERMISSION_LEVEL_GUEST:
                    permissionLabel = "目标";
                    break;
                case Constants.PERMISSION_LEVEL_OWNER:
                    permissionLabel = "所有者";
                    break;
                default:
                    permissionLabel = "（未知）";
            }
            Pop pop = new Pop(
                    new PopKey(projectKey.getLongId(), targetUserKey.getStringId()),
                    permissionLevel,
                    "赋予用户 " + targetUserKey.getStringId() + " " + permissionLabel + "权限"
            );
            popMaintainService.insertOrUpdate(pop);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removePermission(StringIdKey ownerUserKey, PermissionRemoveInfo permissionRemoveInfo)
            throws HandlerException {
        try {
            LongIdKey projectKey = permissionRemoveInfo.getProjectKey();
            StringIdKey targetUserKey = permissionRemoveInfo.getUserKey();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认用户存在。
            handlerValidator.makeSureUserExists(ownerUserKey);
            handlerValidator.makeSureUserExists(targetUserKey);

            // 3. 确认资产目录存在。
            handlerValidator.makeSureProjectExists(projectKey);

            // 4. 确认用户有权限操作指定的资产目录。
            handlerValidator.makeSureUserPermittedForProject(ownerUserKey, projectKey);

            // 5. 通过入口信息组合权限实体主键，并进行存在删除操作。
            PopKey popKey = new PopKey(projectKey.getLongId(), targetUserKey.getStringId());
            popMaintainService.deleteIfExists(popKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
