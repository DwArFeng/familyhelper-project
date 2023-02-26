package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 备忘录提醒驱动异常。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class MemoRemindDriverException extends HandlerException {

    private static final long serialVersionUID = 1888877901531825017L;

    public MemoRemindDriverException() {
    }

    public MemoRemindDriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemoRemindDriverException(String message) {
        super(message);
    }

    public MemoRemindDriverException(Throwable cause) {
        super(cause);
    }
}
