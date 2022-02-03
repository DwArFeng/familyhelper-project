package com.dwarfeng.familyhelper.project.impl.configuration;

import com.dwarfeng.familyhelper.project.impl.bean.entity.HibernateProject;
import com.dwarfeng.familyhelper.project.impl.dao.preset.ProjectPresetCriteriaMaker;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;
    private final Mapper mapper;

    private final ProjectPresetCriteriaMaker projectPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template, Mapper mapper,
            ProjectPresetCriteriaMaker projectPresetCriteriaMaker
    ) {
        this.template = template;
        this.mapper = mapper;
        this.projectPresetCriteriaMaker = projectPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Project, HibernateProject>
    projectHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(Project.class, HibernateProject.class, mapper),
                HibernateProject.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Project, HibernateProject> projectHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(Project.class, HibernateProject.class, mapper),
                HibernateProject.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Project, HibernateProject> projectHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(Project.class, HibernateProject.class, mapper),
                HibernateProject.class,
                projectPresetCriteriaMaker
        );
    }
}
