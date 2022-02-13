package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PreTaskResetInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TaskCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TaskUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.exception.*;
import com.dwarfeng.familyhelper.project.stack.handler.TaskOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class TaskOperateHandlerImpl implements TaskOperateHandler {

    private final UserMaintainService userMaintainService;
    private final ProjectMaintainService projectMaintainService;
    private final PopMaintainService popMaintainService;
    private final TaskMaintainService taskMaintainService;
    private final PreTaskMaintainService preTaskMaintainService;

    public TaskOperateHandlerImpl(
            UserMaintainService userMaintainService,
            ProjectMaintainService projectMaintainService,
            PopMaintainService popMaintainService,
            TaskMaintainService taskMaintainService,
            PreTaskMaintainService preTaskMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.projectMaintainService = projectMaintainService;
        this.popMaintainService = popMaintainService;
        this.taskMaintainService = taskMaintainService;
        this.preTaskMaintainService = preTaskMaintainService;
    }

    @Override
    public LongIdKey createTask(StringIdKey userKey, TaskCreateInfo taskCreateInfo) throws HandlerException {
        try {
            LongIdKey projectKey = taskCreateInfo.getProjectKey();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认工程存在。
            makeSureProjectExists(projectKey);

            // 3. 确认用户有权限操作指定的工程。
            makeSureUserPermittedForProject(userKey, projectKey);

            // 4. 根据 taskCreateInfo 以及创建的规则组合任务实体。
            new Date();
            Task task = new Task(
                    null, projectKey, taskCreateInfo.getType(), taskCreateInfo.getName(),
                    taskCreateInfo.getDescription(), taskCreateInfo.getRemark(), Constants.TASK_STATUS_IN_PROGRESS,
                    new Date(), new Date(), null, taskCreateInfo.getTotalMissionCount(), 0
            );

            // 5. 插入任务实体，并返回生成的主键。
            return taskMaintainService.insert(task);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateTask(StringIdKey userKey, TaskUpdateInfo taskUpdateInfo) throws HandlerException {
        try {
            LongIdKey taskKey = taskUpdateInfo.getTaskKey();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认任务存在。
            makeSureTaskExists(taskKey);

            // 3. 确认用户有权限操作指定的银行卡。
            makeSureUserPermittedForTask(userKey, taskKey);

            // 4. 根据 taskUpdateInfo 以及更新的规则设置任务实体。
            Task task = taskMaintainService.get(taskKey);
            task.setType(taskUpdateInfo.getType());
            task.setName(taskUpdateInfo.getName());
            task.setDescription(taskUpdateInfo.getDescription());
            task.setRemark(taskUpdateInfo.getRemark());
            task.setTotalMissionCount(taskUpdateInfo.getTotalMissionCount());
            task.setFinishedMissionCount(taskUpdateInfo.getFinishedMissionCount());
            task.setModifiedDate(new Date());

            // 5. 更新任务实体。
            taskMaintainService.update(task);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeTask(StringIdKey userKey, LongIdKey taskKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认任务存在。
            makeSureTaskExists(taskKey);

            // 3. 确认用户有权限操作指定的银行卡。
            makeSureUserPermittedForTask(userKey, taskKey);

            // 4. 确认此任务没有任何后置任务，以保证任务删除后其它的任务状态不受影响。
            markSurePostTaskNotExists(taskKey);

            // 5. 删除指定的任务。
            taskMaintainService.delete(taskKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void resetPreTask(StringIdKey userKey, PreTaskResetInfo preTaskResetInfo) throws HandlerException {
        try {
            // TODO 业务逻辑待实现。
            throw new HandlerException();
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public boolean refreshTaskStatus(StringIdKey userKey, LongIdKey taskKey) throws HandlerException {
        try {
            // TODO 业务逻辑待实现。
            throw new HandlerException();
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void finishTask(StringIdKey userKey, LongIdKey taskKey) throws HandlerException {
        try {
            // TODO 业务逻辑待实现。
            throw new HandlerException();
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (Objects.isNull(userKey) || !userMaintainService.exists(userKey)) {
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

    private void makeSureTaskExists(LongIdKey taskKey) throws HandlerException {
        try {
            if (Objects.isNull(taskKey) || !taskMaintainService.exists(taskKey)) {
                throw new TaskNotExistsException(taskKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
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

    private void makeSureUserPermittedForTask(StringIdKey userKey, LongIdKey taskKey) throws HandlerException {
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

    private void markSurePostTaskNotExists(LongIdKey taskKey) throws HandlerException {
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
}
