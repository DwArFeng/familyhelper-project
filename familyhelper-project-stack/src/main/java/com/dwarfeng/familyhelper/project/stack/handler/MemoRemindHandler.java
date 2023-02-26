package com.dwarfeng.familyhelper.project.stack.handler;

import com.dwarfeng.familyhelper.project.stack.exception.MemoRemindDriverException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 备忘录提醒处理器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindHandler extends Handler {

    /**
     * 执行备忘录提醒动作。
     *
     * @param memoRemindDriverInfoKey 被触发的备忘录提醒驱动信息的主键。
     * @throws MemoRemindDriverException 备忘录提醒驱动器异常。
     */
    void remind(LongIdKey memoRemindDriverInfoKey) throws HandlerException;
}
