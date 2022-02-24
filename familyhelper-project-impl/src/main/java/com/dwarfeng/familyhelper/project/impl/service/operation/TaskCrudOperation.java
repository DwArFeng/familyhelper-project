package com.dwarfeng.familyhelper.project.impl.service.operation;

import com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.familyhelper.project.stack.bean.entity.TimePoint;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.familyhelper.project.stack.cache.PreTaskCache;
import com.dwarfeng.familyhelper.project.stack.cache.TaskCache;
import com.dwarfeng.familyhelper.project.stack.cache.TimePointCache;
import com.dwarfeng.familyhelper.project.stack.dao.PreTaskDao;
import com.dwarfeng.familyhelper.project.stack.dao.TaskDao;
import com.dwarfeng.familyhelper.project.stack.dao.TimePointDao;
import com.dwarfeng.familyhelper.project.stack.service.PreTaskMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.TimePointMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskCrudOperation implements BatchCrudOperation<LongIdKey, Task> {

    private final TaskDao taskDao;
    private final TaskCache taskCache;

    private final PreTaskDao preTaskDao;
    private final PreTaskCache preTaskCache;

    private final TimePointDao timePointDao;
    private final TimePointCache timePointCache;

    @Value("${cache.timeout.entity.task}")
    private long taskTimeout;

    public TaskCrudOperation(
            TaskDao taskDao, TaskCache taskCache,
            PreTaskDao preTaskDao, PreTaskCache preTaskCache,
            TimePointDao timePointDao, TimePointCache timePointCache
    ) {
        this.taskDao = taskDao;
        this.taskCache = taskCache;
        this.preTaskDao = preTaskDao;
        this.preTaskCache = preTaskCache;
        this.timePointDao = timePointDao;
        this.timePointCache = timePointCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return taskCache.exists(key) || taskDao.exists(key);
    }

    @Override
    public Task get(LongIdKey key) throws Exception {
        if (taskCache.exists(key)) {
            return taskCache.get(key);
        } else {
            if (!taskDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Task task = taskDao.get(key);
            taskCache.push(task, taskTimeout);
            return task;
        }
    }

    @Override
    public LongIdKey insert(Task task) throws Exception {
        taskCache.push(task, taskTimeout);
        return taskDao.insert(task);
    }

    @Override
    public void update(Task task) throws Exception {
        taskCache.push(task, taskTimeout);
        taskDao.update(task);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除相关的前置任务。
        List<TpKey> presetTaskKeys = preTaskDao.lookup(
                PreTaskMaintainService.CHILD_FOR_SUBJECT_TASK, new Object[]{key}
        ).stream().map(PreTask::getKey).collect(Collectors.toList());
        preTaskCache.batchDelete(presetTaskKeys);
        preTaskDao.batchDelete(presetTaskKeys);
        presetTaskKeys = preTaskDao.lookup(PreTaskMaintainService.CHILD_FOR_OBJECT_TASK, new Object[]{key})
                .stream().map(PreTask::getKey).collect(Collectors.toList());
        preTaskCache.batchDelete(presetTaskKeys);
        preTaskDao.batchDelete(presetTaskKeys);

        // 删除与任务相关的时间点。
        List<LongIdKey> timePointKeys = timePointDao.lookup(TimePointMaintainService.CHILD_FOR_TASK, new Object[]{key})
                .stream().map(TimePoint::getKey).collect(Collectors.toList());
        timePointCache.batchDelete(timePointKeys);
        timePointDao.batchDelete(timePointKeys);

        // 删除账本实体自身。
        taskCache.delete(key);
        taskDao.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return taskCache.allExists(keys) || taskDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return taskCache.nonExists(keys) && taskDao.nonExists(keys);
    }

    @Override
    public List<Task> batchGet(List<LongIdKey> keys) throws Exception {
        if (taskCache.allExists(keys)) {
            return taskCache.batchGet(keys);
        } else {
            if (!taskDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Task> tasks = taskDao.batchGet(keys);
            taskCache.batchPush(tasks, taskTimeout);
            return tasks;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Task> tasks) throws Exception {
        taskCache.batchPush(tasks, taskTimeout);
        return taskDao.batchInsert(tasks);
    }

    @Override
    public void batchUpdate(List<Task> tasks) throws Exception {
        taskCache.batchPush(tasks, taskTimeout);
        taskDao.batchUpdate(tasks);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
