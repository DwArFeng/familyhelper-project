package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.bean.entity.FastJsonMemo;
import com.dwarfeng.familyhelper.project.sdk.bean.entity.FastJsonMemoRemindDriverInfo;
import com.dwarfeng.familyhelper.project.sdk.bean.entity.FastJsonUser;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoRemindInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 备忘录提醒信息。
 *
 * @author DwArFeng
 * @since 1.2.1
 */
public class FastJsonMemoRemindInfo implements Dto {

    private static final long serialVersionUID = 688790910416210689L;

    public static FastJsonMemoRemindInfo of(MemoRemindInfo memoRemindInfo) {
        if (Objects.isNull(memoRemindInfo)) {
            return null;
        } else {
            return new FastJsonMemoRemindInfo(
                    FastJsonMemo.of(memoRemindInfo.getMemo()),
                    FastJsonMemoRemindDriverInfo.of(memoRemindInfo.getMemoRemindDriverInfo()),
                    FastJsonUser.of(memoRemindInfo.getUser())
            );
        }
    }

    @JSONField(name = "memo", ordinal = 1)
    private FastJsonMemo memo;

    @JSONField(name = "memo_remind_driver_info", ordinal = 2)
    private FastJsonMemoRemindDriverInfo memoRemindDriverInfo;

    @JSONField(name = "user", ordinal = 3)
    private FastJsonUser user;

    public FastJsonMemoRemindInfo() {
    }

    public FastJsonMemoRemindInfo(
            FastJsonMemo memo, FastJsonMemoRemindDriverInfo memoRemindDriverInfo, FastJsonUser user
    ) {
        this.memo = memo;
        this.memoRemindDriverInfo = memoRemindDriverInfo;
        this.user = user;
    }

    public FastJsonMemo getMemo() {
        return memo;
    }

    public void setMemo(FastJsonMemo memo) {
        this.memo = memo;
    }

    public FastJsonMemoRemindDriverInfo getMemoRemindDriverInfo() {
        return memoRemindDriverInfo;
    }

    public void setMemoRemindDriverInfo(FastJsonMemoRemindDriverInfo memoRemindDriverInfo) {
        this.memoRemindDriverInfo = memoRemindDriverInfo;
    }

    public FastJsonUser getUser() {
        return user;
    }

    public void setUser(FastJsonUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FastJsonMemoRemindInfo{" +
                "memo=" + memo +
                ", memoRemindDriverInfo=" + memoRemindDriverInfo +
                ", user=" + user +
                '}';
    }
}
