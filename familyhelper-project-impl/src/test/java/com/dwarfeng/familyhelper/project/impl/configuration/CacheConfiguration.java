package com.dwarfeng.familyhelper.project.impl.configuration;

import com.dwarfeng.familyhelper.project.sdk.bean.entity.FastJsonProject;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;
    private final Mapper mapper;

    @Value("${cache.prefix.entity.project}")
    private String projectPrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template, Mapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Project, FastJsonProject> projectRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonProject>) template,
                new LongIdStringKeyFormatter(projectPrefix),
                new DozerBeanTransformer<>(Project.class, FastJsonProject.class, mapper)
        );
    }
}
