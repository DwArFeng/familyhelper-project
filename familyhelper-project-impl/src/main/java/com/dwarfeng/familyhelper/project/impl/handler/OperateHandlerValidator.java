package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.entity.*;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.exception.*;
import com.dwarfeng.familyhelper.project.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 操作处理器验证器。
 *
 * <p>
 * 为操作处理器提供公共的验证方法。
 *
 * @author DwArFeng
 * @since 1.0.1
 */
@Component
public class OperateHandlerValidator {

    private final ProjectMaintainService projectMaintainService;
    private final TaskMaintainService taskMaintainService;
    private final TimePointMaintainService timePointMaintainService;
    private final MemoMaintainService memoMaintainService;
    private final MemoFileInfoMaintainService memoFileInfoMaintainService;
    private final UserMaintainService userMaintainService;
    private final PopMaintainService popMaintainService;
    private final PreTaskMaintainService preTaskMaintainService;

    public OperateHandlerValidator(
            ProjectMaintainService projectMaintainService,
            TaskMaintainService taskMaintainService,
            TimePointMaintainService timePointMaintainService,
            MemoMaintainService memoMaintainService,
            MemoFileInfoMaintainService memoFileInfoMaintainService,
            UserMaintainService userMaintainService,
            PopMaintainService popMaintainService,
            PreTaskMaintainService preTaskMaintainService
    ) {
        this.projectMaintainService = projectMaintainService;
        this.taskMaintainService = taskMaintainService;
        this.timePointMaintainService = timePointMaintainService;
        this.memoMaintainService = memoMaintainService;
        this.memoFileInfoMaintainService = memoFileInfoMaintainService;
        this.userMaintainService = userMaintainService;
        this.popMaintainService = popMaintainService;
        this.preTaskMaintainService = preTaskMaintainService;
    }

    public void makeSureProjectExists(LongIdKey projectKey) throws HandlerException {
        try {
            if (Objects.isNull(projectKey) || !projectMaintainService.exists(projectKey)) {
                throw new ProjectNotExistsException(projectKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureTaskExists(LongIdKey taskKey) throws HandlerException {
        try {
            if (Objects.isNull(taskKey) || !taskMaintainService.exists(taskKey)) {
                throw new TaskNotExistsException(taskKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureTimePointExists(LongIdKey timePointKey) throws HandlerException {
        try {
            if (Objects.isNull(timePointKey) || !timePointMaintainService.exists(timePointKey)) {
                throw new TimePointNotExistsException(timePointKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureMemoExists(LongIdKey memoKey) throws HandlerException {
        try {
            if (Objects.isNull(memoKey) || !memoMaintainService.exists(memoKey)) {
                throw new MemoNotExistsException(memoKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureMemoFileExists(LongIdKey memoFileKey) throws HandlerException {
        try {
            if (!memoFileInfoMaintainService.exists(memoFileKey)) {
                throw new MemoFileNotExistsException(memoFileKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (!userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public void makeSureUserPermittedForProject(StringIdKey userKey, LongIdKey projectKey)
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

    public void makeSureUserPermittedForTask(StringIdKey userKey, LongIdKey taskKey) throws HandlerException {
        try {
            // 1. 查找指定的银行卡是否绑定账本，如果不绑定账本，则抛出银行卡状态异常。
            Task task = taskMaintainService.get(taskKey);
            if (Objects.isNull(task.getProjectKey())) {
                throw new IllegalTaskStateException(taskKey);
            }

            // 2. 取出银行卡的账本外键，判断用户是否拥有该账本的权限。
            makeSureUserPermittedForProject(userKey, task.getProjectKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserPermittedForTimePoint(StringIdKey userKey, LongIdKey timePointKey)
            throws HandlerException {
        try {
            // 1. 查找指定的银行卡是否绑定账本，如果不绑定账本，则抛出银行卡状态异常。
            TimePoint timePoint = timePointMaintainService.get(timePointKey);
            if (Objects.isNull(timePoint.getTaskKey())) {
                throw new IllegalTimePointStateException(timePointKey);
            }

            // 2. 取出银行卡的账本外键，判断用户是否拥有该账本的权限。
            makeSureUserPermittedForTask(userKey, timePoint.getTaskKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserPermittedForMemo(StringIdKey userKey, LongIdKey memoKey) throws HandlerException {
        try {
            // 1. 查找指定的项目文件信息是否绑定项目，如果不绑定项目，则抛出项目文件信息状态异常。
            Memo memo = memoMaintainService.get(memoKey);
            if (Objects.isNull(memo.getUserKey())) {
                throw new IllegalMemoStateException(memoKey);
            }

            // 2. 取出项目文件信息的项目外键，判断用户是否拥有该项目的权限。
            makeSureUserIdentical(userKey, memo.getUserKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserPermittedForMemoFileInfo(StringIdKey userKey, LongIdKey memoFileInfoKey)
            throws HandlerException {
        try {
            // 1. 查找指定的项目文件信息是否绑定项目，如果不绑定项目，则抛出项目文件信息状态异常。
            MemoFileInfo memoFileInfo = memoFileInfoMaintainService.get(memoFileInfoKey);
            if (Objects.isNull(memoFileInfo.getMemoKey())) {
                throw new IllegalMemoFileStateException(memoFileInfoKey);
            }

            // 2. 取出项目文件信息的项目外键，判断用户是否拥有该项目的权限。
            makeSureUserPermittedForMemo(userKey, memoFileInfo.getMemoKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureProjectStatusValid(int status) throws HandlerException {
        if (status == Constants.PROJECT_STATUS_IN_PROGRESS) {
            return;
        }
        if (status == Constants.PROJECT_STATUS_FINISHED) {
            return;
        }
        throw new InvalidProjectStatusException(status);
    }

    public void makeSurePermissionLevelValid(int permissionLevel) throws HandlerException {
        if (permissionLevel == Constants.PERMISSION_LEVEL_GUEST) {
            return;
        }
        if (permissionLevel == Constants.PERMISSION_LEVEL_MODIFIER) {
            return;
        }
        throw new InvalidPermissionLevelException(permissionLevel);
    }

    public void markSurePostTaskNotExists(LongIdKey taskKey) throws HandlerException {
        try {
            long postTaskCount = preTaskMaintainService.lookup(
                    PreTaskMaintainService.CHILD_FOR_OBJECT_TASK, new Object[]{taskKey}
            ).getCount();
            if (postTaskCount > 0) {
                throw new PostTaskExistsException(taskKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserIdentical(StringIdKey userKey, StringIdKey targetUserKey) throws HandlerException {
        if (!Objects.equals(userKey, targetUserKey)) {
            throw new UserNotIdenticalException(userKey, targetUserKey);
        }
    }

    public void makeSureUserIdenticalForMemo(StringIdKey userKey, LongIdKey memoKey) throws HandlerException {
        try {
            // 1. 查找指定的备忘录是否绑定账本，如果不绑定账本，则抛出备忘录状态异常。
            Memo memo = memoMaintainService.get(memoKey);
            if (Objects.isNull(memo.getUserKey())) {
                throw new IllegalMemoStateException(memoKey);
            }

            // 2. 取出备忘录的账本外键，备忘录的用户是否与操作用户一致。
            makeSureUserIdentical(userKey, memo.getUserKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
