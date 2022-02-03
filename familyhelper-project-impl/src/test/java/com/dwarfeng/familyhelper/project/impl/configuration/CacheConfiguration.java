package com.dwarfeng.familyhelper.project.impl.configuration;

import com.dwarfeng.familyhelper.project.sdk.bean.entity.FastJsonPop;
import com.dwarfeng.familyhelper.project.sdk.bean.entity.FastJsonProject;
import com.dwarfeng.familyhelper.project.sdk.bean.entity.FastJsonUser;
import com.dwarfeng.familyhelper.project.sdk.bean.key.formatter.PopStringKeyFormatter;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.familyhelper.project.stack.bean.entity.User;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
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
    @Value("${cache.prefix.entity.user}")
    private String userPrefix;
    @Value("${cache.prefix.entity.pop}")
    private String popPrefix;

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

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new DozerBeanTransformer<>(User.class, FastJsonUser.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<PopKey, Pop, FastJsonPop> popRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPop>) template,
                new PopStringKeyFormatter(popPrefix),
                new DozerBeanTransformer<>(Pop.class, FastJsonPop.class, mapper)
        );
    }
}
