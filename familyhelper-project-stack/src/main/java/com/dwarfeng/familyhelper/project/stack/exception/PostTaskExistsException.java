package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 后置任务存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class PostTaskExistsException extends HandlerException {

    private static final long serialVersionUID = -9090694839037275725L;

    private final LongIdKey taskKey;

    public PostTaskExistsException(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public PostTaskExistsException(Throwable cause, LongIdKey taskKey) {
        super(cause);
        this.taskKey = taskKey;
    }

    @Override
    public String getMessage() {
        return "任务 " + taskKey + " 存在后置任务";
    }
}
