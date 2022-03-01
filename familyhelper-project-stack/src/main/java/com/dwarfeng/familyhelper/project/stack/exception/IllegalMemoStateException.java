package com.dwarfeng.familyhelper.project.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 备忘录状态非法异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class IllegalMemoStateException extends HandlerException {

    private static final long serialVersionUID = 6505728684080324434L;

    private final LongIdKey memoKey;

    public IllegalMemoStateException(LongIdKey memoKey) {
        this.memoKey = memoKey;
    }

    public IllegalMemoStateException(Throwable cause, LongIdKey memoKey) {
        super(cause);
        this.memoKey = memoKey;
    }

    @Override
    public String getMessage() {
        return "备忘录 " + memoKey + " 状态异常: 它是否没绑定用户?";
    }
}
