package com.dwarfeng.familyhelper.project.impl.cache;

import com.dwarfeng.familyhelper.project.sdk.bean.entity.FastJsonMemoRemindDriverSupport;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverSupport;
import com.dwarfeng.familyhelper.project.stack.cache.MemoRemindDriverSupportCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MemoRemindDriverSupportCacheImpl implements MemoRemindDriverSupportCache {

    private final RedisBatchBaseCache<StringIdKey, MemoRemindDriverSupport, FastJsonMemoRemindDriverSupport> batchBaseCache;

    public MemoRemindDriverSupportCacheImpl(
            RedisBatchBaseCache<StringIdKey, MemoRemindDriverSupport, FastJsonMemoRemindDriverSupport> batchBaseCache
    ) {
        this.batchBaseCache = batchBaseCache;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(StringIdKey key) throws CacheException {
        return batchBaseCache.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public MemoRemindDriverSupport get(StringIdKey key) throws CacheException {
        return batchBaseCache.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(MemoRemindDriverSupport value, long timeout) throws CacheException {
        batchBaseCache.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(StringIdKey key) throws CacheException {
        batchBaseCache.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        batchBaseCache.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return batchBaseCache.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return batchBaseCache.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<MemoRemindDriverSupport> batchGet(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return batchBaseCache.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<MemoRemindDriverSupport> entities, long timeout) throws CacheException {
        batchBaseCache.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<StringIdKey> keys) throws CacheException {
        batchBaseCache.batchDelete(keys);
    }
}
