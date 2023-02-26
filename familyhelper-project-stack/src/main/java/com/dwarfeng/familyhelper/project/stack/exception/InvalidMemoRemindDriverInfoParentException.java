package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 无效的备忘录通知驱动信息父项异常。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class InvalidMemoRemindDriverInfoParentException extends HandlerException {

    private static final long serialVersionUID = -5309502571023469356L;

    private final LongIdKey memoRemindDriverInfoKey;
    private final InvalidCause invalidCause;

    public InvalidMemoRemindDriverInfoParentException(LongIdKey memoRemindDriverInfoKey, InvalidCause invalidCause) {
        this.memoRemindDriverInfoKey = memoRemindDriverInfoKey;
        this.invalidCause = invalidCause;
    }

    public InvalidMemoRemindDriverInfoParentException(
            Throwable cause, LongIdKey memoRemindDriverInfoKey, InvalidCause invalidCause
    ) {
        super(cause);
        this.memoRemindDriverInfoKey = memoRemindDriverInfoKey;
        this.invalidCause = invalidCause;
    }

    @Override
    public String getMessage() {
        String subMessage = null;
        switch (invalidCause) {
            case PARENT_STATUS_INVALID:
                subMessage = "父项状态无效";
                break;
            case PARENT_USER_INVALID:
                subMessage = "父项用户无效";
                break;
        }
        return "备忘录提醒驱动器信息不合法: " + memoRemindDriverInfoKey + ", 原因是: " + subMessage;
    }

    public enum InvalidCause {
        PARENT_STATUS_INVALID, PARENT_USER_INVALID
    }
}
