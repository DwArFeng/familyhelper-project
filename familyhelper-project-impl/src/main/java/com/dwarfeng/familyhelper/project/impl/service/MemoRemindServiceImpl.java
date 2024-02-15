package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoRemindService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class MemoRemindServiceImpl implements MemoRemindService {

    private final MemoRemindHandler memoRemindHandler;

    private final ServiceExceptionMapper sem;

    public MemoRemindServiceImpl(MemoRemindHandler memoRemindHandler, ServiceExceptionMapper sem) {
        this.memoRemindHandler = memoRemindHandler;
        this.sem = sem;
    }

    @Override
    public void memoRemind(LongIdKey memoRemindDriverInfoKey) throws ServiceException {
        try {
            memoRemindHandler.remind(memoRemindDriverInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("执行备忘录提醒动作时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
