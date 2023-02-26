package com.dwarfeng.familyhelper.project.impl.handler.pusher;

import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoRemindInfo;
import org.springframework.stereotype.Component;

/**
 * 简单的丢弃掉所有信息的推送器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Component
public class DrainPusher extends AbstractPusher {

    public static final String SUPPORT_TYPE = "drain";

    public DrainPusher() {
        super(SUPPORT_TYPE);
    }

    @Override
    public void memoRemindHappened(MemoRemindInfo memoRemindInfo) {
    }

    @Override
    public void memoRemindDriveReset() {

    }

    @Override
    public String toString() {
        return "DrainPusher{" +
                "pusherType='" + pusherType + '\'' +
                '}';
    }
}
