package com.dwarfeng.familyhelper.project.impl.service.operation;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.cache.MemoCache;
import com.dwarfeng.familyhelper.project.stack.dao.MemoDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemoCrudOperation implements BatchCrudOperation<LongIdKey, Memo> {

    private final MemoDao memoDao;
    private final MemoCache memoCache;

    @Value("${cache.timeout.entity.memo}")
    private long memoTimeout;

    public MemoCrudOperation(MemoDao memoDao, MemoCache memoCache) {
        this.memoDao = memoDao;
        this.memoCache = memoCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return memoCache.exists(key) || memoDao.exists(key);
    }

    @Override
    public Memo get(LongIdKey key) throws Exception {
        if (memoCache.exists(key)) {
            return memoCache.get(key);
        } else {
            if (!memoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Memo memo = memoDao.get(key);
            memoCache.push(memo, memoTimeout);
            return memo;
        }
    }

    @Override
    public LongIdKey insert(Memo memo) throws Exception {
        memoCache.push(memo, memoTimeout);
        return memoDao.insert(memo);
    }

    @Override
    public void update(Memo memo) throws Exception {
        memoCache.push(memo, memoTimeout);
        memoDao.update(memo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除账本实体自身。
        memoCache.delete(key);
        memoDao.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return memoCache.allExists(keys) || memoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return memoCache.nonExists(keys) && memoDao.nonExists(keys);
    }

    @Override
    public List<Memo> batchGet(List<LongIdKey> keys) throws Exception {
        if (memoCache.allExists(keys)) {
            return memoCache.batchGet(keys);
        } else {
            if (!memoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Memo> memos = memoDao.batchGet(keys);
            memoCache.batchPush(memos, memoTimeout);
            return memos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Memo> memos) throws Exception {
        memoCache.batchPush(memos, memoTimeout);
        return memoDao.batchInsert(memos);
    }

    @Override
    public void batchUpdate(List<Memo> memos) throws Exception {
        memoCache.batchPush(memos, memoTimeout);
        memoDao.batchUpdate(memos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
