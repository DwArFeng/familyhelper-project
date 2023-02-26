package com.dwarfeng.familyhelper.project.stack.handler;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.familyhelper.project.stack.exception.MemoRemindDriverException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 备忘录提醒驱动器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public interface MemoRemindDriver {

    /**
     * 初始化执行服务。
     *
     * <p>
     * 该方法会在驱动器初始化后调用，请将 context 存放在驱动器的字段中。<br>
     * 当驱动器被触发后，执行 {@link DriverContext#remind(LongIdKey)} 方法即可。
     *
     * @param context 驱动器的上下文。
     */
    void init(DriverContext context);

    /**
     * 注册指定的备忘录提醒驱动器信息。
     *
     * @param registerContext 注册上下文。
     * @throws MemoRemindDriverException 备忘录提醒驱动器异常。
     */
    void register(RegisterContext registerContext) throws MemoRemindDriverException;

    /**
     * 解除注册所有的备忘录提醒驱动器信息。
     *
     * @throws MemoRemindDriverException 备忘录提醒驱动器异常。
     */
    void unregisterAll() throws MemoRemindDriverException;

    /**
     * 驱动上下文。
     *
     * @author DwArFeng
     * @since 1.2.0
     */
    interface DriverContext {

        /**
         * 执行备忘录提醒动作。
         *
         * @param memoRemindDriverInfoKey 被触发的备忘录提醒驱动信息的主键。
         * @throws Exception 执行动作时抛出的任何异常。
         */
        void remind(LongIdKey memoRemindDriverInfoKey) throws Exception;
    }

    /**
     * 注册上下文。
     *
     * @author DwArFeng
     * @since 1.2.0
     */
    interface RegisterContext {

        /**
         * 获取上下文中的备忘录提醒驱动信息。
         *
         * @return 上下文中的备忘录提醒驱动信息。
         */
        MemoRemindDriverInfo getMemoRemindDriverInfo();

        /**
         * 获取上下文中对应的备忘录的状态。
         *
         * @return 上下文中对应的备忘录的状态。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        int getStatus() throws Exception;

        /**
         * 获取上下文中对应的备忘录的星标。
         *
         * @return 上下文中对应的备忘录的星标。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        boolean isStarFlag() throws Exception;

        /**
         * 获取上下文中对应的备忘录的优先级。
         *
         * @return 上下文中对应的备忘录的优先级。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        int getPriority() throws Exception;

        /**
         * 获取上下文中对应的备忘录的期望完成时间。
         *
         * @return 上下文中对应的备忘录的期望完成时间。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        Date getExpectedFinishDate() throws Exception;
    }
}
