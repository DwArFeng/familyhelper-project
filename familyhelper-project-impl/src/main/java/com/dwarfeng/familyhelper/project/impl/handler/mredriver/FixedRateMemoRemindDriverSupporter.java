package com.dwarfeng.familyhelper.project.impl.handler.mredriver;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.familyhelper.project.impl.handler.MemoRemindDriverSupporter;
import org.springframework.stereotype.Component;

/**
 * 固定间隔备忘录提醒驱动支持器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Component
public class FixedRateMemoRemindDriverSupporter implements MemoRemindDriverSupporter {

    public static final String SUPPORT_TYPE = "fixed_rate_memo_remind_driver";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "固定频率备忘录提醒驱动器";
    }

    @Override
    public String provideDescription() {
        return "根据指定的间隔定时备忘录提醒驱动，如果某一次备忘录提醒驱动晚于间隔，则后续备忘录提醒驱动的时间会提前，" +
                "以保持频率不变。";
    }

    @Override
    public String provideExampleParam() {
        FixedRateMemoRemindDriverConfig config = new FixedRateMemoRemindDriverConfig(0, 6000);
        return JSON.toJSONString(config, true);
    }
}
