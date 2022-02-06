package com.dwarfeng.familyhelper.project.sdk.util;

/**
 * 常量类。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class Constants {

    public static final int PERMISSION_LEVEL_OWNER = 0;
    public static final int PERMISSION_LEVEL_GUEST = 1;
    public static final int PERMISSION_LEVEL_MODIFIER = 2;

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
