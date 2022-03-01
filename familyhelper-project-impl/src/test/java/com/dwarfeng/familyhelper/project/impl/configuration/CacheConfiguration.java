package com.dwarfeng.familyhelper.project.impl.configuration;

import com.dwarfeng.familyhelper.project.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.project.sdk.bean.key.formatter.PopStringKeyFormatter;
import com.dwarfeng.familyhelper.project.sdk.bean.key.formatter.TpStringKeyFormatter;
import com.dwarfeng.familyhelper.project.stack.bean.entity.*;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
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
    @Value("${cache.prefix.entity.task_type_indicator}")
    private String taskTypeIndicatorPrefix;
    @Value("${cache.prefix.entity.task}")
    private String taskPrefix;
    @Value("${cache.prefix.entity.pre_task}")
    private String preTaskPrefix;
    @Value("${cache.prefix.entity.time_point}")
    private String timePointPrefix;
    @Value("${cache.prefix.entity.memo}")
    private String memoPrefix;

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

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, TaskTypeIndicator, FastJsonTaskTypeIndicator>
    taskTypeIndicatorRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTaskTypeIndicator>) template,
                new StringIdStringKeyFormatter(taskTypeIndicatorPrefix),
                new DozerBeanTransformer<>(TaskTypeIndicator.class, FastJsonTaskTypeIndicator.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Task, FastJsonTask> taskRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTask>) template,
                new LongIdStringKeyFormatter(taskPrefix),
                new DozerBeanTransformer<>(Task.class, FastJsonTask.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<TpKey, PreTask, FastJsonPreTask> preTaskRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPreTask>) template,
                new TpStringKeyFormatter(preTaskPrefix),
                new DozerBeanTransformer<>(PreTask.class, FastJsonPreTask.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, TimePoint, FastJsonTimePoint> timePointRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTimePoint>) template,
                new LongIdStringKeyFormatter(timePointPrefix),
                new DozerBeanTransformer<>(TimePoint.class, FastJsonTimePoint.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Memo, FastJsonMemo> memoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonMemo>) template,
                new LongIdStringKeyFormatter(memoPrefix),
                new DozerBeanTransformer<>(Memo.class, FastJsonMemo.class, mapper)
        );
    }
}
