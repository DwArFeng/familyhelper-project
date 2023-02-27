package com.dwarfeng.familyhelper.project.impl.handler.mredriver;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * FixedRateMemoRemindDriver 配置。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class FixedRateMemoRemindDriverConfig implements Bean {

    public static final int TIME_RANGE_TYPE_ALL = 0;
    public static final int TIME_RANGE_TYPE_BEFORE_EXCEPTED_FINISH_DATE = 1;
    public static final int TIME_RANGE_TYPE_AFTER_EXCEPTED_FINISH_DATE = 2;

    private static final long serialVersionUID = 6670288302631417281L;

    @JSONField(name = "time_range_type", ordinal = 1)
    private int timeRangeType;

    @JSONField(name = "#time_range_type", ordinal = 2, deserialize = false)
    private String timeRangeTypeRem = String.format(
            "%d: 任何时间提醒; %d: 早于预期结束时间时提醒; %d: 晚于预期结束时间时提醒",
            TIME_RANGE_TYPE_ALL,
            TIME_RANGE_TYPE_BEFORE_EXCEPTED_FINISH_DATE,
            TIME_RANGE_TYPE_AFTER_EXCEPTED_FINISH_DATE
    );

    @JSONField(name = "rate", ordinal = 3)
    private long rate;

    public FixedRateMemoRemindDriverConfig() {
    }

    public FixedRateMemoRemindDriverConfig(int timeRangeType, long rate) {
        this.timeRangeType = timeRangeType;
        this.rate = rate;
    }

    public int getTimeRangeType() {
        return timeRangeType;
    }

    public void setTimeRangeType(int timeRangeType) {
        this.timeRangeType = timeRangeType;
    }

    public String getTimeRangeTypeRem() {
        return timeRangeTypeRem;
    }

    public void setTimeRangeTypeRem(String timeRangeTypeRem) {
        this.timeRangeTypeRem = timeRangeTypeRem;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "FixedRateMemoRemindDriverConfig{" +
                "timeRangeType=" + timeRangeType +
                ", rate=" + rate +
                '}';
    }
}
