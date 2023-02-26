package com.dwarfeng.familyhelper.project.impl.handler.mredriver;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.familyhelper.project.impl.handler.MemoRemindDriverSupporter;
import org.springframework.stereotype.Component;

/**
 * Cron 备忘录提醒驱动器支持器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Component
public class CronMemoRemindDriverSupporter implements MemoRemindDriverSupporter {

    public static final String SUPPORT_TYPE = "cron_memo_remind_driver";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "Cron 备忘录提醒驱动器";
    }

    @Override
    public String provideDescription() {
        return "根据指定的 Cron 表达式定时备忘录提醒驱动的备忘录提醒驱动器";
    }

    @Override
    public String provideExampleParam() {
        CronMemoRemindDriverConfig config = new CronMemoRemindDriverConfig(0, "0/2 * * * * *");
        return JSON.toJSONString(config, true);
    }
}
