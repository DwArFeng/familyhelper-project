package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFile;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFileUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFileUploadInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoFileInfo;
import com.dwarfeng.familyhelper.project.stack.exception.*;
import com.dwarfeng.familyhelper.project.stack.handler.MemoFileOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoFileInfoMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.UserMaintainService;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class MemoFileOperateHandlerImpl implements MemoFileOperateHandler {

    private final UserMaintainService userMaintainService;
    private final MemoFileInfoMaintainService memoFileInfoMaintainService;
    private final MemoMaintainService memoMaintainService;
    private final FtpHandler ftpHandler;

    private final KeyFetcher<LongIdKey> keyFetcher;

    public MemoFileOperateHandlerImpl(
            UserMaintainService userMaintainService,
            MemoFileInfoMaintainService memoFileInfoMaintainService,
            MemoMaintainService memoMaintainService,
            FtpHandler ftpHandler,
            KeyFetcher<LongIdKey> keyFetcher
    ) {
        this.userMaintainService = userMaintainService;
        this.memoFileInfoMaintainService = memoFileInfoMaintainService;
        this.memoMaintainService = memoMaintainService;
        this.ftpHandler = ftpHandler;
        this.keyFetcher = keyFetcher;
    }

    @Override
    public MemoFile downloadMemoFile(StringIdKey userKey, LongIdKey memoFileKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认项目文件存在。
            makeSureMemoFileExists(memoFileKey);

            // 3. 获取项目文件对应的项目，并确认用户有权限操作项目。
            MemoFileInfo memoFileInfo = memoFileInfoMaintainService.get(memoFileKey);
            makeSureUserPermittedForMemo(userKey, memoFileInfo.getMemoKey());

            // 4. 下载项目文件。
            byte[] content = ftpHandler.getFileContent(
                    new String[]{FtpConstants.PATH_MEMO_FILE}, getFileName(memoFileKey)
            );

            // 5. 更新文件的查看时间。
            memoFileInfo.setInspectedDate(new Date());
            memoFileInfoMaintainService.update(memoFileInfo);

            // 6. 拼接 MemoFile 并返回。
            return new MemoFile(memoFileInfo.getOriginName(), content);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void uploadMemoFile(StringIdKey userKey, MemoFileUploadInfo memoFileUploadInfo) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认项目文件所属的项目存在。
            LongIdKey memoKey = memoFileUploadInfo.getMemoKey();
            makeSureMemoExists(memoKey);

            // 3. 确认用户有权限操作项目。
            makeSureUserPermittedForMemo(userKey, memoKey);

            // 4. 分配主键。
            LongIdKey memoFileKey = keyFetcher.fetchKey();

            // 5. 项目文件内容并存储（覆盖）。
            byte[] content = memoFileUploadInfo.getContent();
            ftpHandler.storeFile(new String[]{FtpConstants.PATH_MEMO_FILE}, getFileName(memoFileKey), content);

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
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateMemoFile(StringIdKey userKey, MemoFileUpdateInfo memoFileUpdateInfo) throws HandlerException {
        try {
            LongIdKey memoFileKey = memoFileUpdateInfo.getMemoFileKey();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认项目文件信息存在。
            makeSureMemoFileExists(memoFileKey);

            // 3. 确认用户有权限操作项目文件信息。
            makeSureUserPermittedForMemoFileInfo(userKey, memoFileKey);

            // 4. 项目文件内容并存储（覆盖）。
            byte[] content = memoFileUpdateInfo.getContent();
            ftpHandler.storeFile(new String[]{FtpConstants.PATH_MEMO_FILE}, getFileName(memoFileKey), content);

            // 5. 根据 memoFileUpdateInfo 更新字段。
            MemoFileInfo memoFileInfo = memoFileInfoMaintainService.get(memoFileKey);
            memoFileInfo.setOriginName(memoFileUpdateInfo.getOriginName());
            memoFileInfo.setLength(content.length);
            memoFileInfo.setModifiedDate(new Date());
            memoFileInfoMaintainService.update(memoFileInfo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeMemoFile(StringIdKey userKey, LongIdKey memoFileKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认项目文件存在。
            makeSureMemoFileExists(memoFileKey);

            // 3. 获取项目文件对应的项目，并确认用户有权限操作项目。
            MemoFileInfo memoFileInfo = memoFileInfoMaintainService.get(memoFileKey);
            makeSureUserPermittedForMemo(userKey, memoFileInfo.getMemoKey());

            // 4. 如果存在 MemoFile 文件，则删除。
            if (ftpHandler.existsFile(new String[]{FtpConstants.PATH_MEMO_FILE}, getFileName(memoFileKey))) {
                ftpHandler.deleteFile(new String[]{FtpConstants.PATH_MEMO_FILE}, getFileName(memoFileKey));
            }

            // 5. 如果存在 MemoFileInfo 实体，则删除。
            memoFileInfoMaintainService.deleteIfExists(memoFileKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private String getFileName(LongIdKey memoFileKey) {
        return Long.toString(memoFileKey.getLongId());
    }

    private void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (!userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureMemoFileExists(LongIdKey memoFileKey) throws HandlerException {
        try {
            if (!memoFileInfoMaintainService.exists(memoFileKey)) {
                throw new MemoFileNotExistsException(memoFileKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForMemo(StringIdKey userKey, LongIdKey memoKey) throws HandlerException {
        try {
            // 1. 查找指定的项目文件信息是否绑定项目，如果不绑定项目，则抛出项目文件信息状态异常。
            Memo memo = memoMaintainService.get(memoKey);
            if (Objects.isNull(memo.getUserKey())) {
                throw new IllegalMemoStateException(memoKey);
            }

            // 2. 取出项目文件信息的项目外键，判断用户是否拥有该项目的权限。
            makeSureUserIdentical(userKey, memo.getUserKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureMemoExists(LongIdKey memoKey) throws HandlerException {
        try {
            if (Objects.isNull(memoKey) || !memoMaintainService.exists(memoKey)) {
                throw new MemoNotExistsException(memoKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForMemoFileInfo(StringIdKey userKey, LongIdKey memoFileInfoKey)
            throws HandlerException {
        try {
            // 1. 查找指定的项目文件信息是否绑定项目，如果不绑定项目，则抛出项目文件信息状态异常。
            MemoFileInfo memoFileInfo = memoFileInfoMaintainService.get(memoFileInfoKey);
            if (Objects.isNull(memoFileInfo.getMemoKey())) {
                throw new IllegalMemoFileStateException(memoFileInfoKey);
            }

            // 2. 取出项目文件信息的项目外键，判断用户是否拥有该项目的权限。
            makeSureUserPermittedForMemo(userKey, memoFileInfo.getMemoKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserIdentical(StringIdKey userKey, StringIdKey targetUserKey) throws HandlerException {
        if (!Objects.equals(userKey, targetUserKey)) {
            throw new UserNotIdenticalException(userKey, targetUserKey);
        }
    }
}
