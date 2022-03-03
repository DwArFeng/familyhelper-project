package com.dwarfeng.familyhelper.project.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 13000;

    public static final ServiceException.Code PROJECT_NOT_EXISTS =
            new ServiceException.Code(offset(0), "asset catalog not exists");
    public static final ServiceException.Code USER_NOT_EXISTS =
            new ServiceException.Code(offset(10), "user not exists");
    public static final ServiceException.Code USER_NOT_PERMITTED_FOR_PROJECT =
            new ServiceException.Code(offset(20), "user not permitted for project");
    public static final ServiceException.Code INVALID_PERMISSION_LEVEL =
            new ServiceException.Code(offset(30), "invalid permission level");
    public static final ServiceException.Code INVALID_PROJECT_STATUS =
            new ServiceException.Code(offset(40), "invalid project status");
    public static final ServiceException.Code POST_TASK_EXISTS =
            new ServiceException.Code(offset(50), "post task exists");
    public static final ServiceException.Code INVALID_MEMO_STATUS =
            new ServiceException.Code(offset(60), "invalid memo status");
    public static final ServiceException.Code MEMO_NOT_EXISTS =
            new ServiceException.Code(offset(70), "memo not exists");
    public static final ServiceException.Code USER_NOT_IDENTICAL =
            new ServiceException.Code(offset(80), "user not identical");
    public static final ServiceException.Code INVALID_MEMO_FILE_STATUS =
            new ServiceException.Code(offset(90), "invalid memo file status");
    public static final ServiceException.Code MEMO_FILE_NOT_EXISTS =
            new ServiceException.Code(offset(100), "memo file not exists");

    private static int offset(int i) {
        return EXCEPTION_CODE_OFFSET + i;
    }

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        // 设置 EXCEPTION_CODE_OFFSET 的值。
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;

        // 以新的 EXCEPTION_CODE_OFFSET 为基准，更新异常代码的值。
        PROJECT_NOT_EXISTS.setCode(offset(0));
        USER_NOT_EXISTS.setCode(offset(10));
        USER_NOT_PERMITTED_FOR_PROJECT.setCode(offset(20));
        INVALID_PERMISSION_LEVEL.setCode(offset(30));
        INVALID_PROJECT_STATUS.setCode(offset(40));
        POST_TASK_EXISTS.setCode(offset(50));
        INVALID_MEMO_STATUS.setCode(60);
        MEMO_NOT_EXISTS.setCode(70);
        USER_NOT_IDENTICAL.setCode(80);
        INVALID_MEMO_FILE_STATUS.setCode(90);
        MEMO_FILE_NOT_EXISTS.setCode(100);
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
