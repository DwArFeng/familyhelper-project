package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.handler.TimePointOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.TimePointOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class TimePointOperateServiceImpl implements TimePointOperateService {

    private final TimePointOperateHandler timePointOperateHandler;

    private final ServiceExceptionMapper sem;

    public TimePointOperateServiceImpl(TimePointOperateHandler timePointOperateHandler, ServiceExceptionMapper sem) {
        this.timePointOperateHandler = timePointOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createTimePoint(StringIdKey userKey, TimePointCreateInfo timePointCreateInfo) throws ServiceException {
        try {
            return timePointOperateHandler.createTimePoint(userKey, timePointCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("创建时间点时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void updateTimePoint(StringIdKey userKey, TimePointUpdateInfo timePointUpdateInfo) throws ServiceException {
        try {
            timePointOperateHandler.updateTimePoint(userKey, timePointUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新时间点时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeTimePoint(StringIdKey userKey, LongIdKey timePointKey) throws ServiceException {
        try {
            timePointOperateHandler.removeTimePoint(userKey, timePointKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除时间点时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void finishTimePoint(StringIdKey userKey, LongIdKey timePointKey) throws ServiceException {
        try {
            timePointOperateHandler.finishTimePoint(userKey, timePointKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("完成时间点时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
