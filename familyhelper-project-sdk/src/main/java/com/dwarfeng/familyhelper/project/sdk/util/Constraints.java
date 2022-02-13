package com.dwarfeng.familyhelper.project.sdk.util;

/**
 * 约束类。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class Constraints {

    /**
     * 名称的长度约束。
     */
    public static final int LENGTH_NAME = 50;

    /**
     * 标签的长度约束。
     */
    public static final int LENGTH_LABEL = 50;

    /**
     * 备注的长度约束。
     */
    public static final int LENGTH_REMARK = 100;

    /**
     * 用户主键的长度约束。
     */
    public static final int LENGTH_USER = 50;

    private Constraints() {
        throw new IllegalStateException("禁止实例化");
    }
}
