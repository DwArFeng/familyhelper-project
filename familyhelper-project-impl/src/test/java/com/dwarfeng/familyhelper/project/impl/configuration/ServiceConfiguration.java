package com.dwarfeng.familyhelper.project.impl.configuration;

import com.dwarfeng.familyhelper.project.impl.service.operation.ProjectCrudOperation;
import com.dwarfeng.familyhelper.project.impl.service.operation.UserCrudOperation;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.familyhelper.project.stack.bean.entity.User;
import com.dwarfeng.familyhelper.project.stack.dao.ProjectDao;
import com.dwarfeng.sfds.api.integration.subgrade.SnowFlakeLongIdKeyFetcher;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;
    private final DaoConfiguration daoConfiguration;

    private final ProjectCrudOperation projectCrudOperation;
    private final ProjectDao projectDao;
    private final UserCrudOperation userCrudOperation;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration, DaoConfiguration daoConfiguration,
            ProjectCrudOperation projectCrudOperation, ProjectDao projectDao,
            UserCrudOperation userCrudOperation
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.daoConfiguration = daoConfiguration;
        this.projectCrudOperation = projectCrudOperation;
        this.projectDao = projectDao;
        this.userCrudOperation = userCrudOperation;
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
}
