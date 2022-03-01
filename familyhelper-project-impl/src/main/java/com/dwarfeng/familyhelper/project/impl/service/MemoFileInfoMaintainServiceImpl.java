package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoFileInfo;
import com.dwarfeng.familyhelper.project.stack.service.MemoFileInfoMaintainService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.bean.dto.PagedData;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemoFileInfoMaintainServiceImpl implements MemoFileInfoMaintainService {

    private final GeneralBatchCrudService<LongIdKey, MemoFileInfo> crudService;
    private final DaoOnlyEntireLookupService<MemoFileInfo> entireLookupService;
    private final DaoOnlyPresetLookupService<MemoFileInfo> presetLookupService;

    public MemoFileInfoMaintainServiceImpl(
            GeneralBatchCrudService<LongIdKey, MemoFileInfo> crudService,
            DaoOnlyEntireLookupService<MemoFileInfo> entireLookupService,
            DaoOnlyPresetLookupService<MemoFileInfo> presetLookupService
    ) {
        this.crudService = crudService;
        this.entireLookupService = entireLookupService;
        this.presetLookupService = presetLookupService;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(LongIdKey key) throws ServiceException {
        return crudService.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public MemoFileInfo get(LongIdKey key) throws ServiceException {
        return crudService.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public LongIdKey insert(MemoFileInfo element) throws ServiceException {
        return crudService.insert(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void update(MemoFileInfo element) throws ServiceException {
        crudService.update(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(LongIdKey key) throws ServiceException {
        crudService.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public MemoFileInfo getIfExists(LongIdKey key) throws ServiceException {
        return crudService.getIfExists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public LongIdKey insertIfNotExists(MemoFileInfo element) throws ServiceException {
        return crudService.insertIfNotExists(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void updateIfExists(MemoFileInfo element) throws ServiceException {
        crudService.updateIfExists(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void deleteIfExists(LongIdKey key) throws ServiceException {
        crudService.deleteIfExists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public LongIdKey insertOrUpdate(MemoFileInfo element) throws ServiceException {
        return crudService.insertOrUpdate(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<LongIdKey> keys) throws ServiceException {
        return crudService.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<LongIdKey> keys) throws ServiceException {
        return crudService.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<MemoFileInfo> batchGet(@SkipRecord List<LongIdKey> keys) throws ServiceException {
        return crudService.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public List<LongIdKey> batchInsert(@SkipRecord List<MemoFileInfo> elements) throws ServiceException {
        return crudService.batchInsert(elements);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchUpdate(@SkipRecord List<MemoFileInfo> elements) throws ServiceException {
        crudService.batchUpdate(elements);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<LongIdKey> keys) throws ServiceException {
        crudService.batchDelete(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<MemoFileInfo> batchGetIfExists(@SkipRecord List<LongIdKey> keys) throws ServiceException {
        return crudService.batchGetIfExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public List<LongIdKey> batchInsertIfExists(@SkipRecord List<MemoFileInfo> elements) throws ServiceException {
        return crudService.batchInsertIfExists(elements);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchUpdateIfExists(@SkipRecord List<MemoFileInfo> elements) throws ServiceException {
        crudService.batchUpdateIfExists(elements);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDeleteIfExists(@SkipRecord List<LongIdKey> keys) throws ServiceException {
        crudService.batchDeleteIfExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public List<LongIdKey> batchInsertOrUpdate(@SkipRecord List<MemoFileInfo> elements) throws ServiceException {
        return crudService.batchInsertOrUpdate(elements);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public PagedData<MemoFileInfo> lookup() throws ServiceException {
        return entireLookupService.lookup();
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public PagedData<MemoFileInfo> lookup(PagingInfo pagingInfo) throws ServiceException {
        return entireLookupService.lookup(pagingInfo);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public PagedData<MemoFileInfo> lookup(String preset, Object[] objs) throws ServiceException {
        return presetLookupService.lookup(preset, objs);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public PagedData<MemoFileInfo> lookup(String preset, Object[] objs, PagingInfo pagingInfo) throws ServiceException {
        return presetLookupService.lookup(preset, objs, pagingInfo);
    }
}
