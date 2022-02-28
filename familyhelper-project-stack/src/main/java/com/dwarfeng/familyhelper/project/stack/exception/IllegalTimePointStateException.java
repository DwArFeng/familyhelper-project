package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 时间点状态非法异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class IllegalTimePointStateException extends HandlerException {

    private static final long serialVersionUID = 3069676136915111730L;

    private final LongIdKey timePointKey;

    public IllegalTimePointStateException(LongIdKey timePointKey) {
        this.timePointKey = timePointKey;
    }

    public IllegalTimePointStateException(Throwable cause, LongIdKey timePointKey) {
        super(cause);
        this.timePointKey = timePointKey;
    }

    @Override
    public String getMessage() {
        return "时间点 " + timePointKey + " 状态异常: 它是否没绑定任务?";
    }
}
