package com.dwarfeng.familyhelper.project.impl.configuration;

import com.dwarfeng.familyhelper.project.impl.service.operation.MemoCrudOperation;
import com.dwarfeng.familyhelper.project.impl.service.operation.ProjectCrudOperation;
import com.dwarfeng.familyhelper.project.impl.service.operation.TaskCrudOperation;
import com.dwarfeng.familyhelper.project.impl.service.operation.UserCrudOperation;
import com.dwarfeng.familyhelper.project.stack.bean.entity.*;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.familyhelper.project.stack.cache.MemoFileInfoCache;
import com.dwarfeng.familyhelper.project.stack.cache.PopCache;
import com.dwarfeng.familyhelper.project.stack.cache.PreTaskCache;
import com.dwarfeng.familyhelper.project.stack.cache.TaskTypeIndicatorCache;
import com.dwarfeng.familyhelper.project.stack.dao.*;
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
    private final TaskCrudOperation taskCrudOperation;
    private final TaskDao taskDao;
    private final PreTaskDao preTaskDao;
    private final PreTaskCache preTaskCache;
    private final MemoCrudOperation memoCrudOperation;
    private final MemoDao memoDao;
    private final MemoFileInfoDao memoFileInfoDao;
    private final MemoFileInfoCache memoFileInfoCache;

    @Value("${cache.timeout.entity.pop}")
    private long popTimeout;
    @Value("${cache.timeout.entity.task_type_indicator}")
    private long taskTypeIndicatorTimeout;
    @Value("${cache.timeout.entity.pre_task}")
    private long preTaskTimeout;
    @Value("${cache.timeout.entity.memo_file_info}")
    private long memoFileInfoTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            ProjectCrudOperation projectCrudOperation, ProjectDao projectDao,
            UserCrudOperation userCrudOperation,
            PopDao popDao, PopCache popCache,
            TaskTypeIndicatorDao taskTypeIndicatorDao, TaskTypeIndicatorCache taskTypeIndicatorCache,
            TaskCrudOperation taskCrudOperation, TaskDao taskDao,
            PreTaskDao preTaskDao, PreTaskCache preTaskCache,
            MemoCrudOperation memoCrudOperation, MemoDao memoDao,
            MemoFileInfoDao memoFileInfoDao, MemoFileInfoCache memoFileInfoCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.projectCrudOperation = projectCrudOperation;
        this.projectDao = projectDao;
        this.userCrudOperation = userCrudOperation;
        this.popDao = popDao;
        this.popCache = popCache;
        this.taskTypeIndicatorDao = taskTypeIndicatorDao;
        this.taskTypeIndicatorCache = taskTypeIndicatorCache;
        this.taskCrudOperation = taskCrudOperation;
        this.taskDao = taskDao;
        this.preTaskDao = preTaskDao;
        this.preTaskCache = preTaskCache;
        this.memoCrudOperation = memoCrudOperation;
        this.memoDao = memoDao;
        this.memoFileInfoDao = memoFileInfoDao;
        this.memoFileInfoCache = memoFileInfoCache;
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

    @Bean
    public CustomBatchCrudService<LongIdKey, Task> taskBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                taskCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Task> taskDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                taskDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Task> taskDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                taskDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<TpKey, PreTask> preTaskGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                preTaskDao,
                preTaskCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                preTaskTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<PreTask> preTaskDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                preTaskDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<PreTask> preTaskDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                preTaskDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Memo> memoBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                memoCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Memo> memoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                memoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Memo> memoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                memoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, MemoFileInfo> memoFileInfoGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                memoFileInfoDao,
                memoFileInfoCache,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                memoFileInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<MemoFileInfo> memoFileInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                memoFileInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<MemoFileInfo> memoFileInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                memoFileInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
