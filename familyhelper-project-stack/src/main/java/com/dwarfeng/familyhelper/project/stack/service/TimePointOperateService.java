package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 时间点操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TimePointOperateService extends Service {

    /**
     * 创建时间点。
     *
     * @param userKey             时间点的所有者的主键。
     * @param timePointCreateInfo 时间点的创建信息。
     * @return 生成的时间点的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createTimePoint(StringIdKey userKey, TimePointCreateInfo timePointCreateInfo) throws ServiceException;

    /**
     * 更新时间点。
     *
     * @param userKey             时间点的所有者的主键。
     * @param timePointUpdateInfo 时间点的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateTimePoint(StringIdKey userKey, TimePointUpdateInfo timePointUpdateInfo) throws ServiceException;

    /**
     * 删除时间点。
     *
     * @param userKey      时间点的所有者的主键。
     * @param timePointKey 时间点的主键。
     * @throws ServiceException 服务异常。
     */
    void removeTimePoint(StringIdKey userKey, LongIdKey timePointKey) throws ServiceException;

    /**
     * 完成时间点。
     *
     * @param userKey      操作者的主键。
     * @param timePointKey 时间点的主键。
     * @throws ServiceException 服务异常。
     */
    void finishTimePoint(StringIdKey userKey, LongIdKey timePointKey) throws ServiceException;
}
