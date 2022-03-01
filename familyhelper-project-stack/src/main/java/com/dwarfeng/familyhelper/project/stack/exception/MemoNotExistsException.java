package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 备忘录不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class MemoNotExistsException extends HandlerException {

    private static final long serialVersionUID = 5926820376565910762L;

    private final LongIdKey memoKey;

    public MemoNotExistsException(LongIdKey memoKey) {
        this.memoKey = memoKey;
    }

    public MemoNotExistsException(Throwable cause, LongIdKey memoKey) {
        super(cause);
        this.memoKey = memoKey;
    }

    @Override
    public String getMessage() {
        return "备忘录 " + memoKey + " 不存在";
    }
}
