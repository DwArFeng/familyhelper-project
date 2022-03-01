package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.handler.MemoOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class MemoOperateServiceImpl implements MemoOperateService {

    private final MemoOperateHandler memoOperateHandler;

    private final ServiceExceptionMapper sem;

    public MemoOperateServiceImpl(MemoOperateHandler memoOperateHandler, ServiceExceptionMapper sem) {
        this.memoOperateHandler = memoOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createMemo(StringIdKey userKey, MemoCreateInfo memoCreateInfo) throws ServiceException {
        try {
            return memoOperateHandler.createMemo(userKey, memoCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("创建备忘录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void updateMemo(StringIdKey userKey, MemoUpdateInfo memoUpdateInfo) throws ServiceException {
        try {
            memoOperateHandler.updateMemo(userKey, memoUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新备忘录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeMemo(StringIdKey userKey, LongIdKey memoKey) throws ServiceException {
        try {
            memoOperateHandler.removeMemo(userKey, memoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除备忘录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void finishMemo(StringIdKey userKey, LongIdKey memoKey) throws ServiceException {
        try {
            memoOperateHandler.finishMemo(userKey, memoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("完成备忘录时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
