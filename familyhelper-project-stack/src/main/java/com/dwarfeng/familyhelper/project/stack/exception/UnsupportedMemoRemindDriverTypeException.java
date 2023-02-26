package com.dwarfeng.familyhelper.project.stack.exception;

/**
 * 不支持的备忘录提醒驱动器类型异常。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class UnsupportedMemoRemindDriverTypeException extends MemoRemindDriverException {

    private static final long serialVersionUID = 6778504768550403211L;

    private final String type;

    public UnsupportedMemoRemindDriverTypeException(String type) {
        this.type = type;
    }

    public UnsupportedMemoRemindDriverTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的备忘录提醒驱动器类型: " + type;
    }
}
