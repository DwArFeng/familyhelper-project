package com.dwarfeng.familyhelper.project.impl.handler.mredriver;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * CronMemoRemindDriver 配置类。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class CronMemoRemindDriverConfig implements Bean {

    public static final int TIME_RANGE_TYPE_ALL = 0;
    public static final int TIME_RANGE_TYPE_BEFORE_EXCEPTED_FINISH_DATE = 1;
    public static final int TIME_RANGE_TYPE_AFTER_EXCEPTED_FINISH_DATE = 2;

    private static final long serialVersionUID = -767535694621908785L;

    @JSONField(name = "time_range_type", ordinal = 1)
    private int timeRangeType;

    @JSONField(name = "#time_range_type", ordinal = 2)
    private String timeRangeTypeRem = String.format(
            "%d: 任何时间提醒; %d: 早于预期结束时间时提醒; %d: 晚于预期结束时间时提醒",
            TIME_RANGE_TYPE_ALL,
            TIME_RANGE_TYPE_BEFORE_EXCEPTED_FINISH_DATE,
            TIME_RANGE_TYPE_AFTER_EXCEPTED_FINISH_DATE
    );

    @JSONField(name = "cron", ordinal = 3)
    private String cron;

    public CronMemoRemindDriverConfig() {
    }

    public CronMemoRemindDriverConfig(int timeRangeType, String cron) {
        this.timeRangeType = timeRangeType;
        this.cron = cron;
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

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    @Override
    public String toString() {
        return "CronMemoRemindDriverConfig{" +
                "timeRangeType=" + timeRangeType +
                ", cron='" + cron + '\'' +
                '}';
    }
}
