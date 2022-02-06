package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.ProjectCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.ProjectUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.exception.*;
import com.dwarfeng.familyhelper.project.stack.handler.ProjectOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.PopMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.ProjectMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ProjectOperateHandlerImpl implements ProjectOperateHandler {

    private final UserMaintainService userMaintainService;
    private final ProjectMaintainService projectMaintainService;
    private final PopMaintainService popMaintainService;

    public ProjectOperateHandlerImpl(
            UserMaintainService userMaintainService,
            ProjectMaintainService projectMaintainService,
            PopMaintainService popMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.projectMaintainService = projectMaintainService;
        this.popMaintainService = popMaintainService;
    }

    @Override
    public LongIdKey createProject(StringIdKey userKey, ProjectCreateInfo projectCreateInfo)
            throws HandlerException {
        try {
            int status = projectCreateInfo.getStatus();

            // 1. 确认 status 有效。
            makeSureProjectStatusValid(status);

            // 2. 确认用户存在。
            makeSureUserExists(userKey);

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
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateProject(StringIdKey userKey, ProjectUpdateInfo projectUpdateInfo)
            throws HandlerException {
        try {
            LongIdKey projectKey = projectUpdateInfo.getProjectKey();
            int status = projectUpdateInfo.getStatus();

            // 1. 确认 status 有效。
            makeSureProjectStatusValid(status);

            // 2. 确认用户存在。
            makeSureUserExists(userKey);

            // 3. 确认资产目录存在。
            makeSureProjectExists(projectKey);

            // 4. 确认用户有权限操作指定的资产目录。
            makeSureUserPermittedForProject(userKey, projectKey);

            // 5. 根据 projectUpdateInfo 以及更新的规则设置 资产目录 实体。
            Project project = projectMaintainService.get(projectKey);
            project.setName(projectUpdateInfo.getName());
            project.setRemark(projectUpdateInfo.getRemark());

            // 6. 更新资产目录实体。
            projectMaintainService.update(project);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeProject(StringIdKey userKey, LongIdKey projectKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认资产目录存在。
            makeSureProjectExists(projectKey);

            // 3. 确认用户有权限操作指定的资产目录。
            makeSureUserPermittedForProject(userKey, projectKey);

            // 4. 删除指定主键的资产目录。
            projectMaintainService.delete(projectKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

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
            makeSurePermissionLevelValid(permissionLevel);

            // 3. 确认用户存在。
            makeSureUserExists(ownerUserKey);
            makeSureUserExists(targetUserKey);

            // 4. 确认资产目录存在。
            makeSureProjectExists(projectKey);

            // 5. 确认用户有权限操作指定的资产目录。
            makeSureUserPermittedForProject(ownerUserKey, projectKey);

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
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
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
            makeSureUserExists(ownerUserKey);
            makeSureUserExists(targetUserKey);

            // 3. 确认资产目录存在。
            makeSureProjectExists(projectKey);

            // 4. 确认用户有权限操作指定的资产目录。
            makeSureUserPermittedForProject(ownerUserKey, projectKey);

            // 5. 通过入口信息组合权限实体主键，并进行存在删除操作。
            PopKey popKey = new PopKey(projectKey.getLongId(), targetUserKey.getStringId());
            popMaintainService.deleteIfExists(popKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (!userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureProjectExists(LongIdKey projectKey) throws HandlerException {
        try {
            if (Objects.isNull(projectKey) || !projectMaintainService.exists(projectKey)) {
                throw new ProjectNotExistsException(projectKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForProject(StringIdKey userKey, LongIdKey projectKey)
            throws HandlerException {
        try {
            // 1. 构造 Pop 主键。
            PopKey popKey = new PopKey(projectKey.getLongId(), userKey.getStringId());

            // 2. 查看 Pop 实体是否存在，如果不存在，则没有权限。
            if (!popMaintainService.exists(popKey)) {
                throw new UserNotPermittedException(userKey, projectKey);
            }

            // 3. 查看 Pop.permissionLevel 是否为 Pop.PERMISSION_LEVEL_OWNER，如果不是，则没有权限。
            Pop pop = popMaintainService.get(popKey);
            if (!Objects.equals(pop.getPermissionLevel(), Constants.PERMISSION_LEVEL_OWNER)) {
                throw new UserNotPermittedException(userKey, projectKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureProjectStatusValid(int status) throws HandlerException {
        if (status == Constants.PROJECT_STATUS_IN_PROGRESS) {
            return;
        }
        if (status == Constants.PROJECT_STATUS_FINISHED) {
            return;
        }
        throw new InvalidProjectStatusException(status);
    }

    private void makeSurePermissionLevelValid(int permissionLevel) throws HandlerException {
        if (permissionLevel == Constants.PERMISSION_LEVEL_GUEST) {
            return;
        }
        if (permissionLevel == Constants.PERMISSION_LEVEL_MODIFIER) {
            return;
        }
        throw new InvalidPermissionLevelException(permissionLevel);
    }
}
