package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 用户对账本没有权限异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class UserNotPermittedException extends HandlerException {

    private static final long serialVersionUID = -621191100176172894L;

    private final StringIdKey userKey;
    private final LongIdKey projectKey;

    public UserNotPermittedException(StringIdKey userKey, LongIdKey projectKey) {
        this.userKey = userKey;
        this.projectKey = projectKey;
    }

    public UserNotPermittedException(Throwable cause, StringIdKey userKey, LongIdKey projectKey) {
        super(cause);
        this.userKey = userKey;
        this.projectKey = projectKey;
    }

    @Override
    public String getMessage() {
        return "用户 " + userKey + " 没有操作工程 " + projectKey + " 的权限";
    }
}
