package com.dwarfeng.familyhelper.project.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.StartableHandler;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface ResetHandler extends StartableHandler {

    /**
     * 重置备忘录提醒驱动。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetMemoRemindDrive() throws HandlerException;
}
