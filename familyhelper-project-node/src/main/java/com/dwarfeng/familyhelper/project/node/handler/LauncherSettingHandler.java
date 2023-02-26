package com.dwarfeng.familyhelper.project.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_memo_remind_driver_support}")
    private boolean resetMemoRemindDriverSupport;

    @Value("${launcher.online_memo_remind_drive_delay}")
    private long onlineMemoRemindDriveDelay;
    @Value("${launcher.start_memo_remind_drive_delay}")
    private long startMemoRemindDriveDelay;

    @Value("${launcher.start_reset_delay}")
    private long startResetDelay;

    public boolean isResetMemoRemindDriverSupport() {
        return resetMemoRemindDriverSupport;
    }

    public long getOnlineMemoRemindDriveDelay() {
        return onlineMemoRemindDriveDelay;
    }

    public long getStartMemoRemindDriveDelay() {
        return startMemoRemindDriveDelay;
    }

    public long getStartResetDelay() {
        return startResetDelay;
    }
}
