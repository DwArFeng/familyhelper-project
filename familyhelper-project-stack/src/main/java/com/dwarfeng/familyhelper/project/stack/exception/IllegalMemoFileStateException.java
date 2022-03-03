package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 备忘录文件状态非法异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class IllegalMemoFileStateException extends HandlerException {

    private static final long serialVersionUID = 5201012892890555072L;

    private final LongIdKey memoFileKey;

    public IllegalMemoFileStateException(LongIdKey memoFileKey) {
        this.memoFileKey = memoFileKey;
    }

    public IllegalMemoFileStateException(Throwable cause, LongIdKey memoFileKey) {
        super(cause);
        this.memoFileKey = memoFileKey;
    }

    @Override
    public String getMessage() {
        return "备忘录文件 " + memoFileKey + " 状态异常: 它是否没绑定备忘录?";
    }
}
