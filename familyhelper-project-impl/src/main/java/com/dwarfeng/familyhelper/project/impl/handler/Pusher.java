package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoRemindInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 事件推送器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface Pusher {

    /**
     * 返回事件推送器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 事件推送器是否支持指定的类型。
     */
    boolean supportType(String type);

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
