package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.handler.MemoOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemoOperateHandlerImpl implements MemoOperateHandler {

    private final MemoMaintainService memoMaintainService;

    private final HandlerValidator handlerValidator;

    public MemoOperateHandlerImpl(
            MemoMaintainService memoMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.memoMaintainService = memoMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public LongIdKey createMemo(StringIdKey userKey, MemoCreateInfo memoCreateInfo) throws HandlerException {
        try {
            StringIdKey targetUserKey = memoCreateInfo.getUserKey();

            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);
            handlerValidator.makeSureUserExists(targetUserKey);

            // 2. 确认用户一致。
            handlerValidator.makeSureUserIdentical(userKey, targetUserKey);

            // 3. 根据 memoCreateInfo 以及创建的规则组合备忘录实体。
            Date currentDate = new Date();
            Memo memo = new Memo(
                    null, targetUserKey, memoCreateInfo.getProfile(), memoCreateInfo.getRemark(),
                    Constants.MEMO_STATUS_IN_PROGRESS, currentDate, currentDate, null,
                    memoCreateInfo.isStarFlag(), memoCreateInfo.getPriority(), memoCreateInfo.getExpectedFinishDate(),
                    memoCreateInfo.getBrief()
            );

            // 4. 插入备忘录实体，并返回生成的主键。
            return memoMaintainService.insert(memo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateMemo(StringIdKey userKey, MemoUpdateInfo memoUpdateInfo) throws HandlerException {
        try {
            LongIdKey memoKey = memoUpdateInfo.getMemoKey();

            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认备忘录存在。
            handlerValidator.makeSureMemoExists(memoKey);

            // 3. 确认备忘录所属的用户与操作的用户一致。
            handlerValidator.makeSureUserIdenticalForMemo(userKey, memoKey);

            // 4. 根据 memoUpdateInfo 以及更新的规则设置备忘录实体。
            Memo memo = memoMaintainService.get(memoKey);
            memo.setProfile(memoUpdateInfo.getProfile());
            memo.setRemark(memoUpdateInfo.getRemark());
            memo.setStarFlag(memoUpdateInfo.isStarFlag());
            memo.setPriority(memoUpdateInfo.getPriority());
            memo.setModifiedDate(new Date());
            memo.setExpectedFinishDate(memoUpdateInfo.getExpectedFinishDate());
            memo.setBrief(memoUpdateInfo.getBrief());

            // 5. 更新备忘录实体。
            memoMaintainService.update(memo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeMemo(StringIdKey userKey, LongIdKey memoKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认备忘录存在。
            handlerValidator.makeSureMemoExists(memoKey);

            // 3. 确认备忘录所属的用户与操作的用户一致。
            handlerValidator.makeSureUserIdenticalForMemo(userKey, memoKey);

            // 4. 删除指定的备忘录。
            memoMaintainService.delete(memoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void finishMemo(StringIdKey userKey, LongIdKey memoKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 确认备忘录存在。
            handlerValidator.makeSureMemoExists(memoKey);

            // 3. 确认备忘录所属的用户与操作的用户一致。
            handlerValidator.makeSureUserIdenticalForMemo(userKey, memoKey);

            // 4. 设置备忘录属性，使其变为完成状态。
            Date currentDate = new Date();
            Memo memo = memoMaintainService.get(memoKey);
            memo.setStatus(Constants.MEMO_STATUS_FINISHED);
            memo.setModifiedDate(currentDate);
            memo.setFinishedDate(currentDate);

            // 5. 更新备忘录实体。
            memoMaintainService.update(memo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeFinishedMemos(StringIdKey userKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 2. 查询指定的用户的所有已完成的备忘录。
            List<LongIdKey> memoKeys = memoMaintainService.lookup(
                    MemoMaintainService.CHILD_FOR_USER_FINISHED, new Object[]{userKey}
            ).getData().stream().map(Memo::getKey).collect(Collectors.toList());

            // 3. 删除备忘录实体。
            memoMaintainService.batchDelete(memoKeys);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
