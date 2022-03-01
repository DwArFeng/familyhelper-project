package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 用户不一致异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class UserNotIdenticalException extends HandlerException {

    private static final long serialVersionUID = -2056172711974175469L;

    private final StringIdKey userKey;
    private final StringIdKey targetUserKey;

    public UserNotIdenticalException(StringIdKey userKey, StringIdKey targetUserKey) {
        this.userKey = userKey;
        this.targetUserKey = targetUserKey;
    }

    public UserNotIdenticalException(Throwable cause, StringIdKey userKey, StringIdKey targetUserKey) {
        super(cause);
        this.userKey = userKey;
        this.targetUserKey = targetUserKey;
    }

    @Override
    public String getMessage() {
        return "操作用户 " + userKey + " 与目标用户 " + targetUserKey + " 不一致";
    }
}
