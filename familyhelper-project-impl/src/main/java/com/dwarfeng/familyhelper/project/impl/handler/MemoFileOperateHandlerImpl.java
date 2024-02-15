package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFile;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFileUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFileUploadInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoFileInfo;
import com.dwarfeng.familyhelper.project.stack.handler.MemoFileOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoFileInfoMaintainService;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MemoFileOperateHandlerImpl implements MemoFileOperateHandler {

    private final MemoFileInfoMaintainService memoFileInfoMaintainService;
    private final FtpHandler ftpHandler;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final HandlerValidator handlerValidator;

    public MemoFileOperateHandlerImpl(
            MemoFileInfoMaintainService memoFileInfoMaintainService,
            FtpHandler ftpHandler,
            KeyGenerator<LongIdKey> keyGenerator,
            HandlerValidator handlerValidator
    ) {
        this.memoFileInfoMaintainService = memoFileInfoMaintainService;
        this.ftpHandler = ftpHandler;
        this.keyGenerator = keyGenerator;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public MemoFile downloadMemoFile(StringIdKey userKey, LongIdKey memoFileKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目文件存在。
            handlerValidator.makeSureMemoFileExists(memoFileKey);

            // 3. 获取项目文件对应的项目，并确认用户有权限操作项目。
            MemoFileInfo memoFileInfo = memoFileInfoMaintainService.get(memoFileKey);
            handlerValidator.makeSureUserPermittedForMemo(userKey, memoFileInfo.getMemoKey());

            // 4. 下载项目文件。
            byte[] content = ftpHandler.retrieveFile(
                    FtpConstants.PATH_MEMO_FILE, getFileName(memoFileKey)
            );

            // 5. 更新文件的查看时间。
            memoFileInfo.setInspectedDate(new Date());
            memoFileInfoMaintainService.update(memoFileInfo);

            // 6. 拼接 MemoFile 并返回。
            return new MemoFile(memoFileInfo.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("ExtractMethodRecommender")
    @Override
    public void uploadMemoFile(StringIdKey userKey, MemoFileUploadInfo memoFileUploadInfo) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目文件所属的项目存在。
            LongIdKey memoKey = memoFileUploadInfo.getMemoKey();
            handlerValidator.makeSureMemoExists(memoKey);

            // 3. 确认用户有权限操作项目。
            handlerValidator.makeSureUserPermittedForMemo(userKey, memoKey);

            // 4. 分配主键。
            LongIdKey memoFileKey = keyGenerator.generate();

            // 5. 项目文件内容并存储（覆盖）。
            byte[] content = memoFileUploadInfo.getContent();
            ftpHandler.storeFile(FtpConstants.PATH_MEMO_FILE, getFileName(memoFileKey), content);

            // 6. 根据 memoFileUploadInfo 构造 MemoFileInfo，插入或更新。
            Date currentDate = new Date();
            // 映射属性。
            MemoFileInfo memoFileInfo = new MemoFileInfo();
            memoFileInfo.setKey(memoFileKey);
            memoFileInfo.setMemoKey(memoKey);
            memoFileInfo.setOriginName(memoFileUploadInfo.getOriginName());
            memoFileInfo.setLength(memoFileUploadInfo.getContent().length);
            memoFileInfo.setCreatedDate(currentDate);
            memoFileInfo.setModifiedDate(currentDate);
            memoFileInfo.setInspectedDate(currentDate);
            memoFileInfo.setRemark("通过 familyhelper-assets 服务上传/更新项目文件");
            memoFileInfoMaintainService.insertOrUpdate(memoFileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateMemoFile(StringIdKey userKey, MemoFileUpdateInfo memoFileUpdateInfo) throws HandlerException {
        try {
            LongIdKey memoFileKey = memoFileUpdateInfo.getMemoFileKey();

            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目文件信息存在。
            handlerValidator.makeSureMemoFileExists(memoFileKey);

            // 3. 确认用户有权限操作项目文件信息。
            handlerValidator.makeSureUserPermittedForMemoFileInfo(userKey, memoFileKey);

            // 4. 项目文件内容并存储（覆盖）。
            byte[] content = memoFileUpdateInfo.getContent();
            ftpHandler.storeFile(FtpConstants.PATH_MEMO_FILE, getFileName(memoFileKey), content);

            // 5. 根据 memoFileUpdateInfo 更新字段。
            MemoFileInfo memoFileInfo = memoFileInfoMaintainService.get(memoFileKey);
            memoFileInfo.setOriginName(memoFileUpdateInfo.getOriginName());
            memoFileInfo.setLength(content.length);
            memoFileInfo.setModifiedDate(new Date());
            memoFileInfoMaintainService.update(memoFileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeMemoFile(StringIdKey userKey, LongIdKey memoFileKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目文件存在。
            handlerValidator.makeSureMemoFileExists(memoFileKey);

            // 3. 获取项目文件对应的项目，并确认用户有权限操作项目。
            MemoFileInfo memoFileInfo = memoFileInfoMaintainService.get(memoFileKey);
            handlerValidator.makeSureUserPermittedForMemo(userKey, memoFileInfo.getMemoKey());

            // 4. 如果存在 MemoFile 文件，则删除。
            if (ftpHandler.existsFile(FtpConstants.PATH_MEMO_FILE, getFileName(memoFileKey))) {
                ftpHandler.deleteFile(FtpConstants.PATH_MEMO_FILE, getFileName(memoFileKey));
            }

            // 5. 如果存在 MemoFileInfo 实体，则删除。
            memoFileInfoMaintainService.deleteIfExists(memoFileKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private String getFileName(LongIdKey memoFileKey) {
        return Long.toString(memoFileKey.getLongId());
    }
}
