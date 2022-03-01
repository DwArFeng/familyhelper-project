package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.exception.IllegalMemoStateException;
import com.dwarfeng.familyhelper.project.stack.exception.MemoNotExistsException;
import com.dwarfeng.familyhelper.project.stack.exception.UserNotExistsException;
import com.dwarfeng.familyhelper.project.stack.exception.UserNotIdenticalException;
import com.dwarfeng.familyhelper.project.stack.handler.MemoOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.PopMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.ProjectMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class MemoOperateHandlerImpl implements MemoOperateHandler {

    private final UserMaintainService userMaintainService;
    private final MemoMaintainService memoMaintainService;

    public MemoOperateHandlerImpl(
            UserMaintainService userMaintainService,
            ProjectMaintainService projectMaintainService,
            PopMaintainService popMaintainService,
            MemoMaintainService memoMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.memoMaintainService = memoMaintainService;
    }

    @Override
    public LongIdKey createMemo(StringIdKey userKey, MemoCreateInfo memoCreateInfo) throws HandlerException {
        try {
            StringIdKey targetUserKey = memoCreateInfo.getUserKey();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);
            makeSureUserExists(targetUserKey);

            // 2. 确认用户一致。
            makeSureUserIdentical(userKey, targetUserKey);

            // 3. 根据 memoCreateInfo 以及创建的规则组合备忘录实体。
            Date currentDate = new Date();
            Memo memo = new Memo(
                    null, targetUserKey, memoCreateInfo.getProfile(), memoCreateInfo.getRemark(),
                    Constants.MEMO_STATUS_IN_PROGRESS, currentDate, currentDate, null
            );

            // 4. 插入备忘录实体，并返回生成的主键。
            return memoMaintainService.insert(memo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateMemo(StringIdKey userKey, MemoUpdateInfo memoUpdateInfo) throws HandlerException {
        try {
            LongIdKey memoKey = memoUpdateInfo.getMemoKey();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认备忘录存在。
            makeSureMemoExists(memoKey);

            // 3. 确认备忘录所属的用户与操作的用户一致。
            makeSureUserIdenticalForMemo(userKey, memoKey);

            // 4. 根据 memoUpdateInfo 以及更新的规则设置备忘录实体。
            Memo memo = memoMaintainService.get(memoKey);
            memo.setProfile(memoUpdateInfo.getProfile());
            memo.setRemark(memoUpdateInfo.getRemark());
            memo.setModifiedDate(new Date());

            // 5. 更新备忘录实体。
            memoMaintainService.update(memo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeMemo(StringIdKey userKey, LongIdKey memoKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认备忘录存在。
            makeSureMemoExists(memoKey);

            // 3. 确认备忘录所属的用户与操作的用户一致。
            makeSureUserIdenticalForMemo(userKey, memoKey);

            // 4. 删除指定的备忘录。
            memoMaintainService.delete(memoKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void finishMemo(StringIdKey userKey, LongIdKey memoKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认备忘录存在。
            makeSureMemoExists(memoKey);

            // 3. 确认备忘录所属的用户与操作的用户一致。
            makeSureUserIdenticalForMemo(userKey, memoKey);

            // 4. 设置备忘录属性，使其变为完成状态。
            Date currentDate = new Date();
            Memo memo = memoMaintainService.get(memoKey);
            memo.setStatus(Constants.MEMO_STATUS_FINISHED);
            memo.setModifiedDate(currentDate);
            memo.setFinishedDate(currentDate);

            // 5. 更新备忘录实体。
            memoMaintainService.update(memo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (Objects.isNull(userKey) || !userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserIdentical(StringIdKey userKey, StringIdKey targetUserKey) throws HandlerException {
        if (!Objects.equals(userKey, targetUserKey)) {
            throw new UserNotIdenticalException(userKey, targetUserKey);
        }
    }

    private void makeSureMemoExists(LongIdKey memoKey) throws HandlerException {
        try {
            if (Objects.isNull(memoKey) || !memoMaintainService.exists(memoKey)) {
                throw new MemoNotExistsException(memoKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserIdenticalForMemo(StringIdKey userKey, LongIdKey memoKey) throws HandlerException {
        try {
            // 1. 查找指定的备忘录是否绑定账本，如果不绑定账本，则抛出备忘录状态异常。
            Memo memo = memoMaintainService.get(memoKey);
            if (Objects.isNull(memo.getUserKey())) {
                throw new IllegalMemoStateException(memoKey);
            }

            // 2. 取出备忘录的账本外键，备忘录的用户是否与操作用户一致。
            makeSureUserIdentical(userKey, memo.getUserKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
