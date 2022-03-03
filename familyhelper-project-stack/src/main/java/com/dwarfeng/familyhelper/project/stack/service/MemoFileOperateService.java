package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFile;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFileUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFileUploadInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 备忘录文件操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface MemoFileOperateService extends Service {

    /**
     * 下载备忘录文件。
     *
     * @param userKey     执行用户主键。
     * @param memoFileKey 备忘录文件主键。
     * @return 备忘录文件。
     * @throws ServiceException 服务异常。
     */
    MemoFile downloadMemoFile(StringIdKey userKey, LongIdKey memoFileKey) throws ServiceException;

    /**
     * 上传备忘录文件。
     *
     * @param userKey            执行用户主键。
     * @param memoFileUploadInfo 备忘录文件上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadMemoFile(StringIdKey userKey, MemoFileUploadInfo memoFileUploadInfo) throws ServiceException;

    /**
     * 更新备忘录文件。
     *
     * @param userKey            执行用户主键。
     * @param memoFileUpdateInfo 备忘录文件更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateMemoFile(StringIdKey userKey, MemoFileUpdateInfo memoFileUpdateInfo) throws ServiceException;

    /**
     * 删除备忘录文件。
     *
     * @param userKey     执行用户主键。
     * @param memoFileKey 备忘录文件主键。
     * @throws ServiceException 服务异常。
     */
    void removeMemoFile(StringIdKey userKey, LongIdKey memoFileKey) throws ServiceException;
}
