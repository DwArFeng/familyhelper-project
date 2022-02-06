package com.dwarfeng.familyhelper.project.node.configuration;

import com.dwarfeng.familyhelper.project.impl.service.operation.ProjectCrudOperation;
import com.dwarfeng.familyhelper.project.impl.service.operation.UserCrudOperation;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.familyhelper.project.stack.bean.entity.TaskTypeIndicator;
import com.dwarfeng.familyhelper.project.stack.bean.entity.User;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.cache.PopCache;
import com.dwarfeng.familyhelper.project.stack.cache.TaskTypeIndicatorCache;
import com.dwarfeng.familyhelper.project.stack.dao.PopDao;
import com.dwarfeng.familyhelper.project.stack.dao.ProjectDao;
import com.dwarfeng.familyhelper.project.stack.dao.TaskTypeIndicatorDao;
import com.dwarfeng.sfds.api.integration.subgrade.SnowFlakeLongIdKeyFetcher;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

    private final ProjectCrudOperation projectCrudOperation;
    private final ProjectDao projectDao;
    private final UserCrudOperation userCrudOperation;
    private final PopDao popDao;
    private final PopCache popCache;
    private final TaskTypeIndicatorDao taskTypeIndicatorDao;
    private final TaskTypeIndicatorCache taskTypeIndicatorCache;

    @Value("${cache.timeout.entity.pop}")
    private long popTimeout;
    @Value("${cache.timeout.entity.task_type_indicator}")
    private long taskTypeIndicatorTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            ProjectCrudOperation projectCrudOperation, ProjectDao projectDao,
            UserCrudOperation userCrudOperation,
            PopDao popDao, PopCache popCache,
            TaskTypeIndicatorDao taskTypeIndicatorDao, TaskTypeIndicatorCache taskTypeIndicatorCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.projectCrudOperation = projectCrudOperation;
        this.projectDao = projectDao;
        this.userCrudOperation = userCrudOperation;
        this.popDao = popDao;
        this.popCache = popCache;
        this.taskTypeIndicatorDao = taskTypeIndicatorDao;
        this.taskTypeIndicatorCache = taskTypeIndicatorCache;
    }

    @Bean
    public KeyFetcher<LongIdKey> longIdKeyKeyFetcher() {
        return new SnowFlakeLongIdKeyFetcher();
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Project> projectBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                projectCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Project> projectDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                projectDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Project> projectDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                projectDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, User> userBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                userCrudOperation,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<PopKey, Pop> popGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                popDao,
                popCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                popTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Pop> popDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                popDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Pop> popDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                popDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, TaskTypeIndicator>
    taskTypeIndicatorGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                taskTypeIndicatorDao,
                taskTypeIndicatorCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                taskTypeIndicatorTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<TaskTypeIndicator> taskTypeIndicatorDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                taskTypeIndicatorDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
