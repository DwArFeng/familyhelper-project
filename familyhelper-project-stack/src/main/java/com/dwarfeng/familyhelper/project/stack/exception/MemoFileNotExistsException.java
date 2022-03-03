package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 备忘录文件不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class MemoFileNotExistsException extends HandlerException {

    private static final long serialVersionUID = -6049138136732405949L;

    private final LongIdKey memoFileKey;

    public MemoFileNotExistsException(LongIdKey memoFileKey) {
        this.memoFileKey = memoFileKey;
    }

    public MemoFileNotExistsException(Throwable cause, LongIdKey memoFileKey) {
        super(cause);
        this.memoFileKey = memoFileKey;
    }

    @Override
    public String getMessage() {
        return "备忘录文件 " + memoFileKey + " 不存在";
    }
}
