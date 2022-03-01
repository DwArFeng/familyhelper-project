package com.dwarfeng.familyhelper.project.stack.handler;

import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 备忘录操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface MemoOperateHandler extends Handler {

    /**
     * 创建备忘录。
     *
     * @param userKey        备忘录的所有者的主键。
     * @param memoCreateInfo 备忘录的创建信息。
     * @return 生成的备忘录的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createMemo(StringIdKey userKey, MemoCreateInfo memoCreateInfo) throws HandlerException;

    /**
     * 更新备忘录。
     *
     * @param userKey        备忘录的所有者的主键。
     * @param memoUpdateInfo 备忘录的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateMemo(StringIdKey userKey, MemoUpdateInfo memoUpdateInfo) throws HandlerException;

    /**
     * 删除备忘录。
     *
     * @param userKey 备忘录的所有者的主键。
     * @param memoKey 备忘录的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeMemo(StringIdKey userKey, LongIdKey memoKey) throws HandlerException;

    /**
     * 完成备忘录。
     *
     * @param userKey 操作者的主键。
     * @param memoKey 备忘录的主键。
     * @throws HandlerException 处理器异常。
     */
    void finishMemo(StringIdKey userKey, LongIdKey memoKey) throws HandlerException;
}
