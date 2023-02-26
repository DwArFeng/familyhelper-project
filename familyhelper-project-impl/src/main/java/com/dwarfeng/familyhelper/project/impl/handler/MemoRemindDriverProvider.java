package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriver;

/**
 * 备忘录提醒驱动器提供器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindDriverProvider {

    /**
     * 返回提供器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 提供器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 提供备忘录提醒驱动器。
     *
     * @return 被提供的备忘录提醒驱动器。
     */
    MemoRemindDriver provide();
}
