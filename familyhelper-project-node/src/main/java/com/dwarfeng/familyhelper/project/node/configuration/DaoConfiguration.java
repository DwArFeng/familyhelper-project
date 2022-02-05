package com.dwarfeng.familyhelper.project.node.configuration;

import com.dwarfeng.familyhelper.project.impl.bean.entity.HibernatePop;
import com.dwarfeng.familyhelper.project.impl.bean.entity.HibernateProject;
import com.dwarfeng.familyhelper.project.impl.bean.entity.HibernateUser;
import com.dwarfeng.familyhelper.project.impl.bean.key.HibernatePopKey;
import com.dwarfeng.familyhelper.project.impl.dao.preset.PopPresetCriteriaMaker;
import com.dwarfeng.familyhelper.project.impl.dao.preset.ProjectPresetCriteriaMaker;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.familyhelper.project.stack.bean.entity.User;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
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
    private final PopPresetCriteriaMaker popPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template, Mapper mapper,
            ProjectPresetCriteriaMaker projectPresetCriteriaMaker,
            PopPresetCriteriaMaker popPresetCriteriaMaker
    ) {
        this.template = template;
        this.mapper = mapper;
        this.projectPresetCriteriaMaker = projectPresetCriteriaMaker;
        this.popPresetCriteriaMaker = popPresetCriteriaMaker;
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

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(User.class, HibernateUser.class, mapper),
                HibernateUser.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateBatchBaseDao<PopKey, HibernatePopKey, Pop, HibernatePop> popHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(PopKey.class, HibernatePopKey.class, mapper),
                new DozerBeanTransformer<>(Pop.class, HibernatePop.class, mapper),
                HibernatePop.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Pop, HibernatePop> popHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(Pop.class, HibernatePop.class, mapper),
                HibernatePop.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Pop, HibernatePop> popHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(Pop.class, HibernatePop.class, mapper),
                HibernatePop.class,
                popPresetCriteriaMaker
        );
    }
}
