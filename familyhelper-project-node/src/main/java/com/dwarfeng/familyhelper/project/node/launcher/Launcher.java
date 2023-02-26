package com.dwarfeng.familyhelper.project.node.launcher;

import com.dwarfeng.familyhelper.project.node.handler.LauncherSettingHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoRemindDriveQosService;
import com.dwarfeng.familyhelper.project.stack.service.MemoRemindDriverSupportMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.ResetQosService;
import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Launcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        ApplicationUtil.launch(new String[]{
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

            // 是否重置备忘录提醒驱动器支持。
            if (launcherSettingHandler.isResetMemoRemindDriverSupport()) {
                LOGGER.info("重置备忘录提醒驱动器支持...");
                MemoRemindDriverSupportMaintainService maintainService = ctx.getBean(
                        MemoRemindDriverSupportMaintainService.class
                );
                try {
                    maintainService.reset();
                } catch (ServiceException e) {
                    LOGGER.warn("备忘录提醒驱动器支持重置失败，异常信息如下", e);
                }
            }

            // 拿出程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
            ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

            // 处理备忘录提醒驱动处理器的启动选项。
            MemoRemindDriveQosService memoRemindDriveQosService = ctx.getBean(MemoRemindDriveQosService.class);
            // 备忘录提醒驱动处理器是否上线备忘录提醒驱动服务。
            long onlineDriveDelay = launcherSettingHandler.getOnlineMemoRemindDriveDelay();
            if (onlineDriveDelay == 0) {
                LOGGER.info("立即上线备忘录提醒驱动服务...");
                try {
                    memoRemindDriveQosService.online();
                } catch (ServiceException e) {
                    LOGGER.error("无法上线备忘录提醒驱动服务，异常原因如下", e);
                }
            } else if (onlineDriveDelay > 0) {
                LOGGER.info(onlineDriveDelay + " 毫秒后上线备忘录提醒驱动服务...");
                scheduler.schedule(
                        () -> {
                            LOGGER.info("上线备忘录提醒驱动服务...");
                            try {
                                memoRemindDriveQosService.online();
                            } catch (ServiceException e) {
                                LOGGER.error("无法上线备忘录提醒驱动服务，异常原因如下", e);
                            }
                        },
                        new Date(System.currentTimeMillis() + onlineDriveDelay)
                );
            }
            // 备忘录提醒驱动处理器是否启动备忘录提醒驱动服务。
            long startMemoRemindDriveDelay = launcherSettingHandler.getStartMemoRemindDriveDelay();
            if (startMemoRemindDriveDelay == 0) {
                LOGGER.info("立即启动备忘录提醒驱动服务...");
                try {
                    memoRemindDriveQosService.online();
                } catch (ServiceException e) {
                    LOGGER.error("无法启动备忘录提醒驱动服务，异常原因如下", e);
                }
            } else if (startMemoRemindDriveDelay > 0) {
                LOGGER.info(startMemoRemindDriveDelay + " 毫秒后启动备忘录提醒驱动服务...");
                scheduler.schedule(
                        () -> {
                            LOGGER.info("启动备忘录提醒驱动服务...");
                            try {
                                memoRemindDriveQosService.start();
                            } catch (ServiceException e) {
                                LOGGER.error("无法启动备忘录提醒驱动服务，异常原因如下", e);
                            }
                        },
                        new Date(System.currentTimeMillis() + startMemoRemindDriveDelay)
                );
            }

            // 处理重置处理器的启动选项。
            ResetQosService resetQosService = ctx.getBean(ResetQosService.class);
            // 重置处理器是否启动重置服务。
            long startResetDelay = launcherSettingHandler.getStartResetDelay();
            if (startResetDelay == 0) {
                LOGGER.info("立即启动重置服务...");
                try {
                    resetQosService.start();
                } catch (ServiceException e) {
                    LOGGER.error("无法启动重置服务，异常原因如下", e);
                }
            } else if (startResetDelay > 0) {
                LOGGER.info(startResetDelay + " 毫秒后启动重置服务...");
                scheduler.schedule(
                        () -> {
                            LOGGER.info("启动重置服务...");
                            try {
                                resetQosService.start();
                            } catch (ServiceException e) {
                                LOGGER.error("无法启动重置服务，异常原因如下", e);
                            }
                        },
                        new Date(System.currentTimeMillis() + startResetDelay)
                );
            }
        });
    }
}
