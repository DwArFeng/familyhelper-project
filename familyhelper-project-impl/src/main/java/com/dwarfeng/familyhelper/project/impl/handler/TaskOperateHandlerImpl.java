package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.PreTaskResetInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TaskCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TaskUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.familyhelper.project.stack.handler.TaskOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.TaskMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskOperateHandlerImpl implements TaskOperateHandler {

    private final TaskMaintainService taskMaintainService;

    private final HandlerValidator handlerValidator;

    public TaskOperateHandlerImpl(
            TaskMaintainService taskMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.taskMaintainService = taskMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public LongIdKey createTask(StringIdKey userKey, TaskCreateInfo taskCreateInfo) throws HandlerException {
        try {
            LongIdKey projectKey = taskCreateInfo.getProjectKey();

            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认工程存在。
            handlerValidator.makeSureProjectExists(projectKey);

            // 3. 确认用户有权限操作指定的工程。
            handlerValidator.makeSureUserPermittedForProject(userKey, projectKey);

            // 4. 根据 taskCreateInfo 以及创建的规则组合任务实体。
            Date currentDate = new Date();
            Task task = new Task(
                    null, projectKey, taskCreateInfo.getType(), taskCreateInfo.getName(),
                    taskCreateInfo.getRemark(), Constants.TASK_STATUS_IN_PROGRESS,
                    currentDate, currentDate, null, taskCreateInfo.getTotalMissionCount(), 0
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
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);

            // 3. 确认用户有权限操作指定的银行卡。
            handlerValidator.makeSureUserPermittedForTask(userKey, taskKey);

            // 4. 根据 taskUpdateInfo 以及更新的规则设置任务实体。
            Task task = taskMaintainService.get(taskKey);
            task.setType(taskUpdateInfo.getType());
            task.setName(taskUpdateInfo.getName());
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
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);

            // 3. 确认用户有权限操作指定的银行卡。
            handlerValidator.makeSureUserPermittedForTask(userKey, taskKey);

            // 4. 确认此任务没有任何后置任务，以保证任务删除后其它的任务状态不受影响。
            handlerValidator.markSurePostTaskNotExists(taskKey);

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
}
