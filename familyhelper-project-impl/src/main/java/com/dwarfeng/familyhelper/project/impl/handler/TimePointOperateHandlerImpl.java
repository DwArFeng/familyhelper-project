package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointCreateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.dto.TimePointUpdateInfo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.familyhelper.project.stack.bean.entity.TimePoint;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.exception.*;
import com.dwarfeng.familyhelper.project.stack.handler.TimePointOperateHandler;
import com.dwarfeng.familyhelper.project.stack.service.PopMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.TaskMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.TimePointMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class TimePointOperateHandlerImpl implements TimePointOperateHandler {

    private final UserMaintainService userMaintainService;
    private final TaskMaintainService taskMaintainService;
    private final PopMaintainService popMaintainService;
    private final TimePointMaintainService timePointMaintainService;

    public TimePointOperateHandlerImpl(
            UserMaintainService userMaintainService,
            TaskMaintainService taskMaintainService,
            PopMaintainService popMaintainService,
            TimePointMaintainService timePointMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.taskMaintainService = taskMaintainService;
        this.popMaintainService = popMaintainService;
        this.timePointMaintainService = timePointMaintainService;
    }

    @Override
    public LongIdKey createTimePoint(StringIdKey userKey, TimePointCreateInfo timePointCreateInfo)
            throws HandlerException {
        try {
            LongIdKey taskKey = timePointCreateInfo.getTaskKey();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认工程存在。
            makeSureTaskExists(taskKey);

            // 3. 确认用户有权限操作指定的工程。
            makeSureUserPermittedForTask(userKey, taskKey);

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
            makeSureUserExists(userKey);

            // 2. 确认时间点存在。
            makeSureTimePointExists(timePointKey);

            // 3. 确认用户有权限操作指定的银行卡。
            makeSureUserPermittedForTask(userKey, timePointKey);

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
            makeSureUserExists(userKey);

            // 2. 确认时间点存在。
            makeSureTimePointExists(timePointKey);

            // 3. 确认用户有权限操作指定的时间点。
            makeSureUserPermittedForTimePoint(userKey, timePointKey);

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
            makeSureUserExists(userKey);

            // 2. 确认时间点存在。
            makeSureTimePointExists(timePointKey);

            // 3. 确认用户有权限操作指定的时间点。
            makeSureUserPermittedForTimePoint(userKey, timePointKey);

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

    private void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (Objects.isNull(userKey) || !userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureTaskExists(LongIdKey taskKey) throws HandlerException {
        try {
            if (Objects.isNull(taskKey) || !taskMaintainService.exists(taskKey)) {
                throw new TaskNotExistsException(taskKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureTimePointExists(LongIdKey timePointKey) throws HandlerException {
        try {
            if (Objects.isNull(timePointKey) || !timePointMaintainService.exists(timePointKey)) {
                throw new TimePointNotExistsException(timePointKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void makeSureUserPermittedForProject(StringIdKey userKey, LongIdKey projectKey)
            throws HandlerException {
        try {
            // 1. 构造 Pop 主键。
            PopKey popKey = new PopKey(projectKey.getLongId(), userKey.getStringId());

            // 2. 查看 Pop 实体是否存在，如果不存在，则没有权限。
            if (!popMaintainService.exists(popKey)) {
                throw new UserNotPermittedException(userKey, projectKey);
            }

            // 3. 查看 Pop.permissionLevel 是否为 Pop.PERMISSION_LEVEL_OWNER，如果不是，则没有权限。
            Pop pop = popMaintainService.get(popKey);
            if (!Objects.equals(pop.getPermissionLevel(), Constants.PERMISSION_LEVEL_OWNER)) {
                throw new UserNotPermittedException(userKey, projectKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForTask(StringIdKey userKey, LongIdKey taskKey) throws HandlerException {
        try {
            // 1. 查找指定的银行卡是否绑定账本，如果不绑定账本，则抛出银行卡状态异常。
            Task task = taskMaintainService.get(taskKey);
            if (Objects.isNull(task.getProjectKey())) {
                throw new IllegalTaskStateException(taskKey);
            }

            // 2. 取出银行卡的账本外键，判断用户是否拥有该账本的权限。
            makeSureUserPermittedForProject(userKey, task.getProjectKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForTimePoint(StringIdKey userKey, LongIdKey timePointKey)
            throws HandlerException {
        try {
            // 1. 查找指定的银行卡是否绑定账本，如果不绑定账本，则抛出银行卡状态异常。
            TimePoint timePoint = timePointMaintainService.get(timePointKey);
            if (Objects.isNull(timePoint.getTaskKey())) {
                throw new IllegalTimePointStateException(timePointKey);
            }

            // 2. 取出银行卡的账本外键，判断用户是否拥有该账本的权限。
            makeSureUserPermittedForTask(userKey, timePoint.getTaskKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
