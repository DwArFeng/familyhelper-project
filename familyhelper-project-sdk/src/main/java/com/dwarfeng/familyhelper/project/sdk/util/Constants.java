package com.dwarfeng.familyhelper.project.sdk.util;

/**
 * 常量类。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class Constants {

    public static final int PROJECT_STATUS_IN_PROGRESS = 0;
    public static final int PROJECT_STATUS_FINISHED = 1;

    public static final int TASK_STATUS_NOT_START = 0;
    public static final int TASK_STATUS_IN_PROGRESS = 1;
    public static final int TASK_STATUS_FINISHED = 2;

    public static final int PERMISSION_LEVEL_OWNER = 0;
    public static final int PERMISSION_LEVEL_GUEST = 1;
    public static final int PERMISSION_LEVEL_MODIFIER = 2;

    public static final int TIME_POINT_STATUS_IN_PROGRESS = 0;
    public static final int TIME_POINT_STATUS_FINISHED = 1;

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
