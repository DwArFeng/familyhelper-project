package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 备忘录提醒驱动信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class MemoRemindDriverInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = 8784352155373973921L;

    private final LongIdKey memoRemindDriverInfoKey;

    public MemoRemindDriverInfoNotExistsException(LongIdKey memoRemindDriverInfoKey) {
        this.memoRemindDriverInfoKey = memoRemindDriverInfoKey;
    }

    public MemoRemindDriverInfoNotExistsException(Throwable cause, LongIdKey memoRemindDriverInfoKey) {
        super(cause);
        this.memoRemindDriverInfoKey = memoRemindDriverInfoKey;
    }

    @Override
    public String getMessage() {
        return "备忘录提醒驱动信息 " + memoRemindDriverInfoKey + " 不存在";
    }
}
