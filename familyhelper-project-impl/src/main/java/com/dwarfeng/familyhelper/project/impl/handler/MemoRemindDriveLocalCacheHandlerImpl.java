package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriveLocalCacheHandler;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriver;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriverHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoRemindDriverInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MemoRemindDriveLocalCacheHandlerImpl implements MemoRemindDriveLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, MemoRemindDriver> handler;

    public MemoRemindDriveLocalCacheHandlerImpl(MemoRemindDriverFetcher memoRemindDriverFetcher) {
        handler = new GeneralLocalCacheHandler<>(memoRemindDriverFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public MemoRemindDriver get(LongIdKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(LongIdKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() throws HandlerException {
        handler.clear();
    }

    @Component
    public static class MemoRemindDriverFetcher implements Fetcher<LongIdKey, MemoRemindDriver> {

        private final MemoRemindDriverInfoMaintainService memoRemindDriverInfoMaintainService;

        private final MemoRemindDriverHandler memoRemindDriverHandler;

        public MemoRemindDriverFetcher(
                MemoRemindDriverInfoMaintainService memoRemindDriverInfoMaintainService,
                MemoRemindDriverHandler memoRemindDriverHandler
        ) {
            this.memoRemindDriverInfoMaintainService = memoRemindDriverInfoMaintainService;
            this.memoRemindDriverHandler = memoRemindDriverHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            return memoRemindDriverInfoMaintainService.exists(key);
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public MemoRemindDriver fetch(LongIdKey key) throws Exception {
            MemoRemindDriverInfo memoRemindDriverInfo = memoRemindDriverInfoMaintainService.get(key);
            return memoRemindDriverHandler.find(memoRemindDriverInfo.getType());
        }
    }
}
