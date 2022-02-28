package com.dwarfeng.familyhelper.project.stack.handler;

import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 时间点操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TimePointOperateHandler extends Handler {

    /**
     * 创建时间点。
     *
     * @param userKey             时间点的所有者的主键。
     * @param timePointCreateInfo 时间点的创建信息。
     * @return 生成的时间点的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createTimePoint(StringIdKey userKey, TimePointCreateInfo timePointCreateInfo) throws HandlerException;

    /**
     * 更新时间点。
     *
     * @param userKey             时间点的所有者的主键。
     * @param timePointUpdateInfo 时间点的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateTimePoint(StringIdKey userKey, TimePointUpdateInfo timePointUpdateInfo) throws HandlerException;

    /**
     * 删除时间点。
     *
     * @param userKey      时间点的所有者的主键。
     * @param timePointKey 时间点的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeTimePoint(StringIdKey userKey, LongIdKey timePointKey) throws HandlerException;

    /**
     * 完成时间点。
     *
     * @param userKey      操作者的主键。
     * @param timePointKey 时间点的主键。
     * @throws HandlerException 处理器异常。
     */
    void finishTimePoint(StringIdKey userKey, LongIdKey timePointKey) throws HandlerException;
}
