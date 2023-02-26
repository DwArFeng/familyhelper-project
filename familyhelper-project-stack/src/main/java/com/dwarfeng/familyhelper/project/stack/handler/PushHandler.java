package com.dwarfeng.familyhelper.project.stack.handler;

import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoRemindInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 推送处理器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface PushHandler extends Handler {

    /**
     * 备忘录提醒动作发生时执行的调度。
     *
     * @throws HandlerException 处理器异常。
     */
    void memoRemindHappened(MemoRemindInfo memoRemindInfo) throws HandlerException;

    /**
     * 备忘录提醒驱动重置时执行的调度。
     *
     * @throws HandlerException 处理器异常。
     */
    void memoRemindDriveReset() throws HandlerException;
}
