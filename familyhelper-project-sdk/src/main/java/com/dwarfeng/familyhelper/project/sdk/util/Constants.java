package com.dwarfeng.familyhelper.project.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 常量类。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

    public static final int PROJECT_STATUS_IN_PROGRESS = 0;
    public static final int PROJECT_STATUS_FINISHED = 1;

    public static final int TASK_STATUS_NOT_START = 0;
    public static final int TASK_STATUS_IN_PROGRESS = 1;
    public static final int TASK_STATUS_FINISHED = 2;

    public static final int PERMISSION_LEVEL_OWNER = 0;
    public static final int PERMISSION_LEVEL_GUEST = 1;
    public static final int PERMISSION_LEVEL_MODIFIER = 2;

    @MemoStatusItem
    public static final int MEMO_STATUS_IN_PROGRESS = 0;
    @MemoStatusItem
    public static final int MEMO_STATUS_FINISHED = 1;

    private static final Lock LOCK = new ReentrantLock();

    private static List<Integer> memoStatusSpace = null;

    /**
     * 获取备忘录状态的空间。
     *
     * @return 备忘录状态的空间。
     * @since 1.2.0
     */
    public static List<Integer> memoStatusSpace() {
        if (Objects.nonNull(memoStatusSpace)) {
            return memoStatusSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(memoStatusSpace)) {
                return memoStatusSpace;
            }
            initMemoStatusSpace();
            return memoStatusSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initMemoStatusSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(MemoStatusItem.class)) {
                continue;
            }
            Integer value;
            try {
                value = (Integer) declaredField.get(null);
                result.add(value);
            } catch (Exception e) {
                LOGGER.error("初始化异常, 请检查代码, 信息如下: ", e);
            }
        }

        memoStatusSpace = Collections.unmodifiableList(result);
    }

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
