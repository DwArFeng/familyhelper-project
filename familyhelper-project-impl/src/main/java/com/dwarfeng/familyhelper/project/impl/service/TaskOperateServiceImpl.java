package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.dto.PreTaskResetInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TaskCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TaskUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.handler.TaskOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.TaskOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class TaskOperateServiceImpl implements TaskOperateService {

    private final TaskOperateHandler taskOperateHandler;

    private final ServiceExceptionMapper sem;

    public TaskOperateServiceImpl(TaskOperateHandler taskOperateHandler, ServiceExceptionMapper sem) {
        this.taskOperateHandler = taskOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createTask(StringIdKey userKey, TaskCreateInfo taskCreateInfo) throws ServiceException {
        try {
            return taskOperateHandler.createTask(userKey, taskCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("创建任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateTask(StringIdKey userKey, TaskUpdateInfo taskUpdateInfo) throws ServiceException {
        try {
            taskOperateHandler.updateTask(userKey, taskUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeTask(StringIdKey userKey, LongIdKey taskKey) throws ServiceException {
        try {
            taskOperateHandler.removeTask(userKey, taskKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void resetPreTask(StringIdKey userKey, PreTaskResetInfo preTaskResetInfo) throws ServiceException {
        try {
            taskOperateHandler.resetPreTask(userKey, preTaskResetInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("重置任务的前置任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean refreshTaskStatus(StringIdKey userKey, LongIdKey taskKey) throws ServiceException {
        try {
            return taskOperateHandler.refreshTaskStatus(userKey, taskKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("刷新指定任务的状态时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void finishTask(StringIdKey userKey, LongIdKey taskKey) throws ServiceException {
        try {
            taskOperateHandler.finishTask(userKey, taskKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("完成任务时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
