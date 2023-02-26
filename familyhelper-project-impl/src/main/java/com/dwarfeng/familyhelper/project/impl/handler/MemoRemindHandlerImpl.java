package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.stack.bean.dto.MemoRemindInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.User;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindHandler;
import com.dwarfeng.familyhelper.project.stack.handler.PushHandler;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.MemoRemindDriverInfoMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MemoRemindHandlerImpl implements MemoRemindHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoRemindHandlerImpl.class);

    private final MemoRemindDriverInfoMaintainService memoRemindDriverInfoMaintainService;
    private final MemoMaintainService memoMaintainService;
    private final UserMaintainService userMaintainService;

    private final PushHandler pushHandler;

    private final HandlerValidator handlerValidator;

    public MemoRemindHandlerImpl(
            MemoRemindDriverInfoMaintainService memoRemindDriverInfoMaintainService,
            MemoMaintainService memoMaintainService,
            UserMaintainService userMaintainService,
            PushHandler pushHandler,
            HandlerValidator handlerValidator
    ) {
        this.memoRemindDriverInfoMaintainService = memoRemindDriverInfoMaintainService;
        this.memoMaintainService = memoMaintainService;
        this.userMaintainService = userMaintainService;
        this.pushHandler = pushHandler;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public void remind(LongIdKey memoRemindDriverInfoKey) throws HandlerException {
        try {
            // 确认 MemoRemindDriverInfo 合法。
            handlerValidator.makeSureMemoRemindDriverInfoValid(memoRemindDriverInfoKey);

            // 获取备忘录提醒相关的实体及字段。
            MemoRemindDriverInfo memoRemindDriverInfo = memoRemindDriverInfoMaintainService.get(
                    memoRemindDriverInfoKey
            );
            Memo memo = memoMaintainService.get(memoRemindDriverInfo.getMemoKey());
            User user = userMaintainService.get(memo.getUserKey());

            // 构造 MemoRemindInfo。
            MemoRemindInfo memoRemindInfo = new MemoRemindInfo(memo, memoRemindDriverInfo, user);

            // 调用推送处理器推送事件。
            try {
                pushHandler.memoRemindHappened(memoRemindInfo);
            } catch (Exception e) {
                LOGGER.warn("推送备忘录提醒动作发生消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
