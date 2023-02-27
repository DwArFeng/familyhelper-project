package com.dwarfeng.familyhelper.project.impl.handler.mredriver;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * FixedDelayMemoRemindDriver 配置。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class FixedDelayMemoRemindDriverConfig implements Bean {

    public static final int TIME_RANGE_TYPE_ALL = 0;
    public static final int TIME_RANGE_TYPE_BEFORE_EXCEPTED_FINISH_DATE = 1;
    public static final int TIME_RANGE_TYPE_AFTER_EXCEPTED_FINISH_DATE = 2;

    private static final long serialVersionUID = -3100896357555151486L;

    @JSONField(name = "time_range_type", ordinal = 1)
    private int timeRangeType;

    @JSONField(name = "#time_range_type", ordinal = 2, deserialize = false)
    private String timeRangeTypeRem = String.format(
            "%d: 任何时间提醒; %d: 早于预期结束时间时提醒; %d: 晚于预期结束时间时提醒",
            TIME_RANGE_TYPE_ALL,
            TIME_RANGE_TYPE_BEFORE_EXCEPTED_FINISH_DATE,
            TIME_RANGE_TYPE_AFTER_EXCEPTED_FINISH_DATE
    );

    @JSONField(name = "delay", ordinal = 3)
    private long delay;

    public FixedDelayMemoRemindDriverConfig() {
    }

    public FixedDelayMemoRemindDriverConfig(int timeRangeType, long delay) {
        this.timeRangeType = timeRangeType;
        this.delay = delay;
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

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "FixedDelayMemoRemindDriverConfig{" +
                "timeRangeType=" + timeRangeType +
                ", delay=" + delay +
                '}';
    }
}
