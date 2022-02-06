package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 工程状态无效异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class InvalidProjectStatusException extends HandlerException {

    private static final long serialVersionUID = 3637649761569946514L;

    private final int status;

    public InvalidProjectStatusException(int status) {
        this.status = status;
    }

    public InvalidProjectStatusException(Throwable cause, int status) {
        super(cause);
        this.status = status;
    }

    @Override
    public String getMessage() {
        return "工程状态 " + status + " 无效";
    }
}
