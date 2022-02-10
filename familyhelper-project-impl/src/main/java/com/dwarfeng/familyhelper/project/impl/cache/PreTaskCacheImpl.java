package com.dwarfeng.familyhelper.project.impl.cache;

import com.dwarfeng.familyhelper.project.sdk.bean.entity.FastJsonPreTask;
import com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.familyhelper.project.stack.cache.PreTaskCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PreTaskCacheImpl implements PreTaskCache {

    private final RedisBatchBaseCache<TpKey, PreTask, FastJsonPreTask> preTaskBatchBaseDelegate;

    public PreTaskCacheImpl(
            RedisBatchBaseCache<TpKey, PreTask, FastJsonPreTask> preTaskBatchBaseDelegate
    ) {
        this.preTaskBatchBaseDelegate = preTaskBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(TpKey key) throws CacheException {
        return preTaskBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public PreTask get(TpKey key) throws CacheException {
        return preTaskBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(PreTask value, long timeout) throws CacheException {
        preTaskBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(TpKey key) throws CacheException {
        preTaskBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        preTaskBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<TpKey> keys) throws CacheException {
        return preTaskBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<TpKey> keys) throws CacheException {
        return preTaskBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<PreTask> batchGet(@SkipRecord List<TpKey> keys) throws CacheException {
        return preTaskBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<PreTask> entities, long timeout) throws CacheException {
        preTaskBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<TpKey> keys) throws CacheException {
        preTaskBatchBaseDelegate.batchDelete(keys);
    }
}
