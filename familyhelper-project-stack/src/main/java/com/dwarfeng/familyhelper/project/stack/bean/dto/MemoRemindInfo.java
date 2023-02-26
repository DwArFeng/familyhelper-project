package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 备忘录提醒信息。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public class MemoRemindInfo implements Dto {

    private static final long serialVersionUID = -8525936683920386904L;

    private Memo memo;
    private MemoRemindDriverInfo memoRemindDriverInfo;
    private User user;

    public MemoRemindInfo() {
    }

    public MemoRemindInfo(Memo memo, MemoRemindDriverInfo memoRemindDriverInfo, User user) {
        this.memo = memo;
        this.memoRemindDriverInfo = memoRemindDriverInfo;
        this.user = user;
    }

    public Memo getMemo() {
        return memo;
    }

    public void setMemo(Memo memo) {
        this.memo = memo;
    }

    public MemoRemindDriverInfo getMemoRemindDriverInfo() {
        return memoRemindDriverInfo;
    }

    public void setMemoRemindDriverInfo(MemoRemindDriverInfo memoRemindDriverInfo) {
        this.memoRemindDriverInfo = memoRemindDriverInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MemoRemindInfo{" +
                "memo=" + memo +
                ", memoRemindDriverInfo=" + memoRemindDriverInfo +
                ", user=" + user +
                '}';
    }
}
