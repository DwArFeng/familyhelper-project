package com.dwarfeng.familyhelper.project.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 备忘录提醒服务。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindService extends Service {

    /**
     * 执行备忘录提醒动作。
     *
     * @param memoRemindDriverInfoKey 被触发的备忘录提醒驱动信息的主键。
     * @throws ServiceException 服务异常。
     */
    void memoRemind(LongIdKey memoRemindDriverInfoKey) throws ServiceException;
}
