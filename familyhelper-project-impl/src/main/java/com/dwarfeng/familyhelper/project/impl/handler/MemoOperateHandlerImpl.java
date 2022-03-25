package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.handler.MemoOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MemoOperateHandlerImpl implements MemoOperateHandler {

    private final MemoMaintainService memoMaintainService;

    private final OperateHandlerValidator operateHandlerValidator;

    public MemoOperateHandlerImpl(
            MemoMaintainService memoMaintainService,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.memoMaintainService = memoMaintainService;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public LongIdKey createMemo(StringIdKey userKey, MemoCreateInfo memoCreateInfo) throws HandlerException {
        try {
            StringIdKey targetUserKey = memoCreateInfo.getUserKey();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);
            operateHandlerValidator.makeSureUserExists(targetUserKey);

            // 2. 确认用户一致。
            operateHandlerValidator.makeSureUserIdentical(userKey, targetUserKey);

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
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认备忘录存在。
            operateHandlerValidator.makeSureMemoExists(memoKey);

            // 3. 确认备忘录所属的用户与操作的用户一致。
            operateHandlerValidator.makeSureUserIdenticalForMemo(userKey, memoKey);

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
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认备忘录存在。
            operateHandlerValidator.makeSureMemoExists(memoKey);

            // 3. 确认备忘录所属的用户与操作的用户一致。
            operateHandlerValidator.makeSureUserIdenticalForMemo(userKey, memoKey);

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
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认备忘录存在。
            operateHandlerValidator.makeSureMemoExists(memoKey);

            // 3. 确认备忘录所属的用户与操作的用户一致。
            operateHandlerValidator.makeSureUserIdenticalForMemo(userKey, memoKey);

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
}
