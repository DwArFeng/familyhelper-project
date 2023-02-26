package com.dwarfeng.familyhelper.project.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 备忘录提醒驱动器处理器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindDriverHandler extends Handler {

    /**
     * 寻找指定的备忘录提醒驱动器。
     *
     * @param type 备忘录提醒驱动器的类型。
     * @return 符合驱动类型的备忘录提醒驱动器。
     * @throws HandlerException 处理器异常。
     */
    MemoRemindDriver find(String type) throws HandlerException;
}
