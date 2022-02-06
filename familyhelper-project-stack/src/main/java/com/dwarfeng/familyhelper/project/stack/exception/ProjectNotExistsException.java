package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 资产目录不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ProjectNotExistsException extends HandlerException {

    private static final long serialVersionUID = 8825433399090685910L;

    private final LongIdKey projectKey;

    public ProjectNotExistsException(LongIdKey projectKey) {
        this.projectKey = projectKey;
    }

    public ProjectNotExistsException(Throwable cause, LongIdKey projectKey) {
        super(cause);
        this.projectKey = projectKey;
    }

    @Override
    public String getMessage() {
        return "工程 " + projectKey + " 不存在";
    }
}
