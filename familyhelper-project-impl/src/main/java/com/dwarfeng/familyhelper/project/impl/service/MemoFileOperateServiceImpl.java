package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFile;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFileUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFileUploadInfo;
import com.dwarfeng.familyhelper.project.stack.handler.MemoFileOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoFileOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class MemoFileOperateServiceImpl implements MemoFileOperateService {

    private final MemoFileOperateHandler memoFileOperateHandler;

    private final ServiceExceptionMapper sem;

    public MemoFileOperateServiceImpl(MemoFileOperateHandler memoFileOperateHandler, ServiceExceptionMapper sem) {
        this.memoFileOperateHandler = memoFileOperateHandler;
        this.sem = sem;
    }

    @Override
    public MemoFile downloadMemoFile(StringIdKey userKey, LongIdKey memoFileKey) throws ServiceException {
        try {
            return memoFileOperateHandler.downloadMemoFile(userKey, memoFileKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("下载项目文件时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void uploadMemoFile(StringIdKey userKey, MemoFileUploadInfo memoFileUploadInfo) throws ServiceException {
        try {
            memoFileOperateHandler.uploadMemoFile(userKey, memoFileUploadInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("上传项目文件时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void updateMemoFile(StringIdKey userKey, MemoFileUpdateInfo memoFileUpdateInfo) throws ServiceException {
        try {
            memoFileOperateHandler.updateMemoFile(userKey, memoFileUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新项目文件时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeMemoFile(StringIdKey userKey, LongIdKey memoFileKey) throws ServiceException {
        try {
            memoFileOperateHandler.removeMemoFile(userKey, memoFileKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除项目文件时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
