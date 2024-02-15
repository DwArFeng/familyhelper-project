package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriveHandler;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriveLocalCacheHandler;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriver;
import com.dwarfeng.familyhelper.project.stack.service.MemoRemindDriveQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class MemoRemindDriveQosServiceImpl implements MemoRemindDriveQosService {

    private final MemoRemindDriveHandler memoRemindDriveHandler;
    private final MemoRemindDriveLocalCacheHandler memoRemindDriveLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public MemoRemindDriveQosServiceImpl(
            MemoRemindDriveHandler memoRemindDriveHandler,
            MemoRemindDriveLocalCacheHandler memoRemindDriveLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.memoRemindDriveHandler = memoRemindDriveHandler;
        this.memoRemindDriveLocalCacheHandler = memoRemindDriveLocalCacheHandler;
        this.sem = sem;
    }

    @PreDestroy
    public void dispose() throws Exception {
        memoRemindDriveHandler.stop();
        memoRemindDriveHandler.offline();
    }

    @Override
    public boolean isOnline() throws ServiceException {
        try {
            return memoRemindDriveHandler.isOnline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断备忘录提醒驱动处理器是否上线时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void online() throws ServiceException {
        try {
            memoRemindDriveHandler.online();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上线备忘录提醒驱动处理器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void offline() throws ServiceException {
        try {
            memoRemindDriveHandler.offline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下线备忘录提醒驱动处理器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isLockHolding() throws ServiceException {
        try {
            return memoRemindDriveHandler.isLockHolding();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断备忘录提醒驱动处理器是否正在持有锁时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isStarted() throws ServiceException {
        try {
            return memoRemindDriveHandler.isStarted();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断备忘录提醒驱动处理器是否启动时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void start() throws ServiceException {
        try {
            memoRemindDriveHandler.start();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("备忘录提醒驱动处理器启动时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void stop() throws ServiceException {
        try {
            memoRemindDriveHandler.stop();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("备忘录提醒驱动处理器停止时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isWorking() throws ServiceException {
        try {
            return memoRemindDriveHandler.isWorking();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断备忘录提醒驱动处理器是否正在工作时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public MemoRemindDriver getDriver(LongIdKey memoRemindDriverInfoKey) throws ServiceException {
        try {
            return memoRemindDriveLocalCacheHandler.get(memoRemindDriverInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    "获取指定的备忘录提醒驱动器信息对应的备忘录提醒驱动器时发生异常", LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            memoRemindDriveLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
