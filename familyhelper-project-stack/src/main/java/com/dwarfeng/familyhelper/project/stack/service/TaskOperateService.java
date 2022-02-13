package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.dto.PreTaskResetInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TaskCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TaskUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 任务操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TaskOperateService extends Service {

    /**
     * 创建任务。
     *
     * @param userKey        任务的所有者的主键。
     * @param taskCreateInfo 任务的创建信息。
     * @return 生成的任务的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createTask(StringIdKey userKey, TaskCreateInfo taskCreateInfo) throws ServiceException;

    /**
     * 更新任务。
     *
     * @param userKey        任务的所有者的主键。
     * @param taskUpdateInfo 任务的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateTask(StringIdKey userKey, TaskUpdateInfo taskUpdateInfo) throws ServiceException;

    /**
     * 删除任务。
     *
     * @param userKey 任务的所有者的主键。
     * @param taskKey 任务的主键。
     * @throws ServiceException 服务异常。
     */
    void removeTask(StringIdKey userKey, LongIdKey taskKey) throws ServiceException;

    /**
     * 重置任务的前置任务。
     *
     * @param userKey          任务的所有者的主键。
     * @param preTaskResetInfo 前置任务重置信息。
     * @throws ServiceException 服务异常。
     */
    void resetPreTask(StringIdKey userKey, PreTaskResetInfo preTaskResetInfo) throws ServiceException;

    /**
     * 刷新指定任务的状态。
     *
     * @param userKey 任务的所有者的主键。
     * @param taskKey 任务的主键。
     * @return 任务的状态是否改变。
     * @throws ServiceException 服务异常。
     */
    boolean refreshTaskStatus(StringIdKey userKey, LongIdKey taskKey) throws ServiceException;

    /**
     * 完成任务。
     *
     * @param userKey 操作者的主键。
     * @param taskKey 任务的主键。
     * @throws ServiceException 服务异常。
     */
    void finishTask(StringIdKey userKey, LongIdKey taskKey) throws ServiceException;
}
