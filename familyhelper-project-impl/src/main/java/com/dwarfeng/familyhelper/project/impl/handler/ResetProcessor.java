package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriveHandler;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriveLocalCacheHandler;
import com.dwarfeng.familyhelper.project.stack.handler.PushHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Component
class ResetProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetProcessor.class);

    private final MemoRemindDriveHandler memoRemindDriveHandler;
    private final MemoRemindDriveLocalCacheHandler memoRemindDriveLocalCacheHandler;

    private final PushHandler pushHandler;

    public ResetProcessor(
            MemoRemindDriveHandler memoRemindDriveHandler,
            MemoRemindDriveLocalCacheHandler memoRemindDriveLocalCacheHandler,
            PushHandler pushHandler
    ) {
        this.memoRemindDriveHandler = memoRemindDriveHandler;
        this.memoRemindDriveLocalCacheHandler = memoRemindDriveLocalCacheHandler;
        this.pushHandler = pushHandler;
    }

    public void resetMemoRemindDrive() throws HandlerException {
        memoRemindDriveHandler.stop();
        memoRemindDriveLocalCacheHandler.clear();
        memoRemindDriveHandler.start();

        try {
            pushHandler.memoRemindDriveReset();
        } catch (Exception e) {
            LOGGER.warn("推送备忘录提醒驱动重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
