package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 任务状态非法异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class IllegalTaskStateException extends HandlerException {

    private static final long serialVersionUID = 2222542748680351650L;

    private final LongIdKey taskKey;

    public IllegalTaskStateException(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public IllegalTaskStateException(Throwable cause, LongIdKey taskKey) {
        super(cause);
        this.taskKey = taskKey;
    }

    @Override
    public String getMessage() {
        return "任务 " + taskKey + " 状态异常: 它是否没绑定工程?";
    }
}
