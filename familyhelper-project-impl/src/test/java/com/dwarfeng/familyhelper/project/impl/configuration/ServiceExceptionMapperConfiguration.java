package com.dwarfeng.familyhelper.project.impl.configuration;

import com.dwarfeng.familyhelper.project.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.familyhelper.project.stack.exception.*;
import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, ServiceException.Code> destination = ServiceExceptionHelper.putDefaultDestination(null);
        destination = com.dwarfeng.ftp.util.ServiceExceptionHelper.putDefaultDestination(destination);
        destination.put(ProjectNotExistsException.class, ServiceExceptionCodes.PROJECT_NOT_EXISTS);
        destination.put(UserNotExistsException.class, ServiceExceptionCodes.USER_NOT_EXISTS);
        destination.put(UserNotPermittedException.class, ServiceExceptionCodes.USER_NOT_PERMITTED_FOR_PROJECT);
        destination.put(InvalidPermissionLevelException.class, ServiceExceptionCodes.INVALID_PERMISSION_LEVEL);
        destination.put(InvalidProjectStatusException.class, ServiceExceptionCodes.INVALID_PROJECT_STATUS);
        destination.put(PostTaskExistsException.class, ServiceExceptionCodes.POST_TASK_EXISTS);
        destination.put(IllegalMemoStateException.class, ServiceExceptionCodes.INVALID_MEMO_STATUS);
        destination.put(MemoNotExistsException.class, ServiceExceptionCodes.MEMO_NOT_EXISTS);
        destination.put(UserNotIdenticalException.class, ServiceExceptionCodes.USER_NOT_IDENTICAL);
        destination.put(IllegalMemoFileStateException.class, ServiceExceptionCodes.INVALID_MEMO_FILE_STATUS);
        destination.put(MemoFileNotExistsException.class, ServiceExceptionCodes.MEMO_FILE_NOT_EXISTS);
        destination.put(MemoRemindDriverException.class, ServiceExceptionCodes.MEMO_REMIND_DRIVER_FAILED);
        destination.put(UnsupportedMemoRemindDriverTypeException.class, ServiceExceptionCodes.MEMO_REMIND_DRIVER_TYPE_UNSUPPORTED);
        destination.put(MemoRemindDriverInfoNotExistsException.class, ServiceExceptionCodes.MEMO_REMIND_DRIVER_INFO_NOT_EXISTS);
        destination.put(MemoRemindDriverInfoDisabledException.class, ServiceExceptionCodes.MEMO_REMIND_DRIVER_INFO_DISABLED);
        destination.put(InvalidMemoRemindDriverInfoParentException.class, ServiceExceptionCodes.INVALID_MEMO_REMIND_DRIVER_INFO_PARENT);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINED);
    }
}
