package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriver;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 备忘录提醒驱动 QOS 服务。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindDriveQosService extends Service {

    /**
     * 备忘录提醒驱动服务是否上线。
     *
     * @return 是否上线。
     * @throws ServiceException 服务异常。
     */
    boolean isOnline() throws ServiceException;

    /**
     * 上线备忘录提醒驱动服务。
     *
     * @throws ServiceException 服务异常。
     */
    void online() throws ServiceException;

    /**
     * 下线备忘录提醒驱动服务。
     *
     * @throws ServiceException 服务异常。
     */
    void offline() throws ServiceException;

    /**
     * 备忘录提醒驱动服务是否正在持有锁。
     *
     * @return 备忘录提醒驱动服务是否正在持有锁。
     * @throws ServiceException 服务异常。
     */
    boolean isLockHolding() throws ServiceException;

    /**
     * 备忘录提醒驱动服务是否启动。
     *
     * @return 备忘录提醒驱动服务是否启动。
     * @throws ServiceException 服务异常。
     */
    boolean isStarted() throws ServiceException;

    /**
     * 备忘录提醒驱动服务启动。
     *
     * @throws ServiceException 服务异常。
     */
    void start() throws ServiceException;

    /**
     * 备忘录提醒驱动服务停止。
     *
     * @throws ServiceException 服务异常。
     */
    void stop() throws ServiceException;

    /**
     * 备忘录提醒驱动服务是否正在工作。
     *
     * @return 备忘录提醒驱动服务是否正在工作。
     * @throws ServiceException 服务异常。
     */
    boolean isWorking() throws ServiceException;

    /**
     * 获取指定的备忘录提醒驱动器信息对应的备忘录提醒驱动器。
     *
     * @param memoRemindDriverInfoKey 指定的备忘录提醒驱动器信息对应的主键。
     * @return 指定的备忘录提醒驱动器信息对应的备忘录提醒驱动器。
     * @throws ServiceException 服务异常。
     */
    MemoRemindDriver getDriver(LongIdKey memoRemindDriverInfoKey) throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
