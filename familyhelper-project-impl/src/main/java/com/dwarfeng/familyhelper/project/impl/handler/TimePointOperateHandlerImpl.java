package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.TimePoint;
import com.dwarfeng.familyhelper.project.stack.handler.TimePointOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.TaskMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.TimePointMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimePointOperateHandlerImpl implements TimePointOperateHandler {

    private final TaskMaintainService taskMaintainService;
    private final TimePointMaintainService timePointMaintainService;

    private final OperateHandlerValidator operateHandlerValidator;

    public TimePointOperateHandlerImpl(
            TaskMaintainService taskMaintainService,
            TimePointMaintainService timePointMaintainService,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.taskMaintainService = taskMaintainService;
        this.timePointMaintainService = timePointMaintainService;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public LongIdKey createTimePoint(StringIdKey userKey, TimePointCreateInfo timePointCreateInfo)
            throws HandlerException {
        try {
            LongIdKey taskKey = timePointCreateInfo.getTaskKey();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认工程存在。
            operateHandlerValidator.makeSureTaskExists(taskKey);

            // 3. 确认用户有权限操作指定的工程。
            operateHandlerValidator.makeSureUserPermittedForTask(userKey, taskKey);

            // 4. 根据 taskCreateInfo 以及创建的规则组合时间点实体。
            TimePoint timePoint = new TimePoint(
                    null, taskKey, timePointCreateInfo.getProfile(), timePointCreateInfo.getRemark(),
                    Constants.TIME_POINT_STATUS_IN_PROGRESS, timePointCreateInfo.getExpectedFinishedDate(), null
            );

            // 5. 插入时间点实体，并返回生成的主键。
            return timePointMaintainService.insert(timePoint);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateTimePoint(StringIdKey userKey, TimePointUpdateInfo timePointUpdateInfo) throws HandlerException {
        try {
            LongIdKey timePointKey = timePointUpdateInfo.getTimePointKey();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认时间点存在。
            operateHandlerValidator.makeSureTimePointExists(timePointKey);

            // 3. 确认用户有权限操作指定的银行卡。
            operateHandlerValidator.makeSureUserPermittedForTask(userKey, timePointKey);

            // 4. 根据 taskUpdateInfo 以及更新的规则设置时间点实体。
            TimePoint timePoint = timePointMaintainService.get(timePointKey);
            timePoint.setProfile(timePointUpdateInfo.getProfile());
            timePoint.setRemark(timePointUpdateInfo.getRemark());
            timePoint.setExpectedFinishedDate(timePointUpdateInfo.getExpectedFinishedDate());

            // 5. 更新时间点实体。
            timePointMaintainService.update(timePoint);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeTimePoint(StringIdKey userKey, LongIdKey timePointKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认时间点存在。
            operateHandlerValidator.makeSureTimePointExists(timePointKey);

            // 3. 确认用户有权限操作指定的时间点。
            operateHandlerValidator.makeSureUserPermittedForTimePoint(userKey, timePointKey);

            // 4. 删除指定的时间点。
            taskMaintainService.delete(timePointKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void finishTimePoint(StringIdKey userKey, LongIdKey timePointKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认时间点存在。
            operateHandlerValidator.makeSureTimePointExists(timePointKey);

            // 3. 确认用户有权限操作指定的时间点。
            operateHandlerValidator.makeSureUserPermittedForTimePoint(userKey, timePointKey);

            // 4. 获得指定的时间点，并修改相应的属性。
            TimePoint timePoint = timePointMaintainService.get(timePointKey);
            timePoint.setActualFinishedDate(new Date());
            timePoint.setStatus(Constants.TIME_POINT_STATUS_FINISHED);

            // 5. 更新时间点。
            timePointMaintainService.update(timePoint);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
