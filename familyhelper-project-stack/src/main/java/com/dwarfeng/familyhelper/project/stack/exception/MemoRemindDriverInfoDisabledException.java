package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 备忘录提醒驱动信息已禁用异常。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class MemoRemindDriverInfoDisabledException extends HandlerException {

    private static final long serialVersionUID = 5885176535974500212L;

    private final LongIdKey memoRemindDriverInfoKey;

    public MemoRemindDriverInfoDisabledException(LongIdKey memoRemindDriverInfoKey) {
        this.memoRemindDriverInfoKey = memoRemindDriverInfoKey;
    }

    public MemoRemindDriverInfoDisabledException(Throwable cause, LongIdKey memoRemindDriverInfoKey) {
        super(cause);
        this.memoRemindDriverInfoKey = memoRemindDriverInfoKey;
    }

    @Override
    public String getMessage() {
        return "备忘录提醒驱动信息 " + memoRemindDriverInfoKey + " 已禁用";
    }
}
