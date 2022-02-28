package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 时间点不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TimePointNotExistsException extends HandlerException {

    private static final long serialVersionUID = 8183205629931450120L;

    private final LongIdKey timePointKey;

    public TimePointNotExistsException(LongIdKey timePointKey) {
        this.timePointKey = timePointKey;
    }

    public TimePointNotExistsException(Throwable cause, LongIdKey timePointKey) {
        super(cause);
        this.timePointKey = timePointKey;
    }

    @Override
    public String getMessage() {
        return "时间点 " + timePointKey + " 不存在";
    }
}
