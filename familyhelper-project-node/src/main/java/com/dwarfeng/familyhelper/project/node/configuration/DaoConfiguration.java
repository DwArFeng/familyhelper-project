package com.dwarfeng.familyhelper.project.node.configuration;

import com.dwarfeng.familyhelper.project.impl.bean.HibernateMapper;
import com.dwarfeng.familyhelper.project.impl.bean.entity.*;
import com.dwarfeng.familyhelper.project.impl.bean.key.HibernatePopKey;
import com.dwarfeng.familyhelper.project.impl.bean.key.HibernateTpKey;
import com.dwarfeng.familyhelper.project.impl.dao.preset.*;
import com.dwarfeng.familyhelper.project.stack.bean.entity.*;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;

    private final ProjectPresetCriteriaMaker projectPresetCriteriaMaker;
    private final PopPresetCriteriaMaker popPresetCriteriaMaker;
    private final TaskPresetCriteriaMaker taskPresetCriteriaMaker;
    private final PreTaskPresetCriteriaMaker preTaskPresetCriteriaMaker;
    private final MemoPresetCriteriaMaker memoPresetCriteriaMaker;
    private final MemoFileInfoPresetCriteriaMaker memoFileInfoPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template,
            ProjectPresetCriteriaMaker projectPresetCriteriaMaker,
            PopPresetCriteriaMaker popPresetCriteriaMaker,
            TaskPresetCriteriaMaker taskPresetCriteriaMaker,
            PreTaskPresetCriteriaMaker preTaskPresetCriteriaMaker,
            MemoPresetCriteriaMaker memoPresetCriteriaMaker,
            MemoFileInfoPresetCriteriaMaker memoFileInfoPresetCriteriaMaker
    ) {
        this.template = template;
        this.projectPresetCriteriaMaker = projectPresetCriteriaMaker;
        this.popPresetCriteriaMaker = popPresetCriteriaMaker;
        this.taskPresetCriteriaMaker = taskPresetCriteriaMaker;
        this.preTaskPresetCriteriaMaker = preTaskPresetCriteriaMaker;
        this.memoPresetCriteriaMaker = memoPresetCriteriaMaker;
        this.memoFileInfoPresetCriteriaMaker = memoFileInfoPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Project, HibernateProject>
    projectHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Project.class, HibernateProject.class, HibernateMapper.class),
                HibernateProject.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Project, HibernateProject> projectHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Project.class, HibernateProject.class, HibernateMapper.class),
                HibernateProject.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Project, HibernateProject> projectHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Project.class, HibernateProject.class, HibernateMapper.class),
                HibernateProject.class,
                projectPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(User.class, HibernateUser.class, HibernateMapper.class),
                HibernateUser.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateBatchBaseDao<PopKey, HibernatePopKey, Pop, HibernatePop> popHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(PopKey.class, HibernatePopKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Pop.class, HibernatePop.class, HibernateMapper.class),
                HibernatePop.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Pop, HibernatePop> popHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Pop.class, HibernatePop.class, HibernateMapper.class),
                HibernatePop.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Pop, HibernatePop> popHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Pop.class, HibernatePop.class, HibernateMapper.class),
                HibernatePop.class,
                popPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, TaskTypeIndicator,
            HibernateTaskTypeIndicator> taskTypeIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        TaskTypeIndicator.class, HibernateTaskTypeIndicator.class, HibernateMapper.class
                ),
                HibernateTaskTypeIndicator.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<TaskTypeIndicator, HibernateTaskTypeIndicator>
    taskTypeIndicatorHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        TaskTypeIndicator.class, HibernateTaskTypeIndicator.class, HibernateMapper.class
                ),
                HibernateTaskTypeIndicator.class
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Task, HibernateTask>
    taskHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Task.class, HibernateTask.class, HibernateMapper.class),
                HibernateTask.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Task, HibernateTask> taskHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Task.class, HibernateTask.class, HibernateMapper.class),
                HibernateTask.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Task, HibernateTask> taskHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Task.class, HibernateTask.class, HibernateMapper.class),
                HibernateTask.class,
                taskPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<TpKey, HibernateTpKey, PreTask, HibernatePreTask>
    preTaskHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(TpKey.class, HibernateTpKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(PreTask.class, HibernatePreTask.class, HibernateMapper.class),
                HibernatePreTask.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<PreTask, HibernatePreTask> preTaskHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(PreTask.class, HibernatePreTask.class, HibernateMapper.class),
                HibernatePreTask.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<PreTask, HibernatePreTask> preTaskHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(PreTask.class, HibernatePreTask.class, HibernateMapper.class),
                HibernatePreTask.class,
                preTaskPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Memo, HibernateMemo>
    memoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Memo.class, HibernateMemo.class, HibernateMapper.class),
                HibernateMemo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Memo, HibernateMemo> memoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Memo.class, HibernateMemo.class, HibernateMapper.class),
                HibernateMemo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Memo, HibernateMemo> memoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Memo.class, HibernateMemo.class, HibernateMapper.class),
                HibernateMemo.class,
                memoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, MemoFileInfo, HibernateMemoFileInfo>
    memoFileInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(MemoFileInfo.class, HibernateMemoFileInfo.class, HibernateMapper.class),
                HibernateMemoFileInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<MemoFileInfo, HibernateMemoFileInfo> memoFileInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(MemoFileInfo.class, HibernateMemoFileInfo.class, HibernateMapper.class),
                HibernateMemoFileInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<MemoFileInfo, HibernateMemoFileInfo> memoFileInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(MemoFileInfo.class, HibernateMemoFileInfo.class, HibernateMapper.class),
                HibernateMemoFileInfo.class,
                memoFileInfoPresetCriteriaMaker
        );
    }
}
