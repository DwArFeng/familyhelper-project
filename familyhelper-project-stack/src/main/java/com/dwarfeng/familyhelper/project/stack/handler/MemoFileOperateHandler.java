package com.dwarfeng.familyhelper.project.stack.handler;

import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFile;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFileUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoFileUploadInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 备忘录文件操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface MemoFileOperateHandler extends Handler {

    /**
     * 下载备忘录文件。
     *
     * @param userKey     执行用户主键。
     * @param memoFileKey 备忘录文件主键。
     * @return 备忘录文件。
     * @throws HandlerException 处理器异常。
     */
    MemoFile downloadMemoFile(StringIdKey userKey, LongIdKey memoFileKey) throws HandlerException;

    /**
     * 上传备忘录文件。
     *
     * @param userKey            执行用户主键。
     * @param memoFileUploadInfo 备忘录文件上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadMemoFile(StringIdKey userKey, MemoFileUploadInfo memoFileUploadInfo) throws HandlerException;

    /**
     * 更新备忘录文件。
     *
     * @param userKey            执行用户主键。
     * @param memoFileUpdateInfo 备忘录文件更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateMemoFile(StringIdKey userKey, MemoFileUpdateInfo memoFileUpdateInfo) throws HandlerException;

    /**
     * 删除备忘录文件。
     *
     * @param userKey     执行用户主键。
     * @param memoFileKey 备忘录文件主键。
     * @throws HandlerException 处理器异常。
     */
    void removeMemoFile(StringIdKey userKey, LongIdKey memoFileKey) throws HandlerException;
}
