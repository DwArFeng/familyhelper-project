package com.dwarfeng.familyhelper.project.impl.handler.mredriver;

import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriver;

/**
 * 备忘录提醒驱动器的抽象实现。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public abstract class AbstractMemoRemindDriver implements MemoRemindDriver {

    protected DriverContext driverContext;

    @Override
    public void init(DriverContext driverContext) {
        this.driverContext = driverContext;
    }

    @Override
    public String toString() {
        return "AbstractRemindDriver{" +
                "driverContext=" + driverContext +
                '}';
    }
}
