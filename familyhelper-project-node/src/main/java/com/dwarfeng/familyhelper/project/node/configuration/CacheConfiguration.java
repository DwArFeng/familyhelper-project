package com.dwarfeng.familyhelper.project.node.configuration;

import com.dwarfeng.familyhelper.project.sdk.bean.FastJsonMapper;
import com.dwarfeng.familyhelper.project.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.project.sdk.bean.key.formatter.PopStringKeyFormatter;
import com.dwarfeng.familyhelper.project.sdk.bean.key.formatter.TpStringKeyFormatter;
import com.dwarfeng.familyhelper.project.stack.bean.entity.*;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;

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
    @Value("${cache.prefix.entity.memo}")
    private String memoPrefix;
    @Value("${cache.prefix.entity.memo_file_info}")
    private String memoFileInfoPrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Project, FastJsonProject> projectRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonProject>) template,
                new LongIdStringKeyFormatter(projectPrefix),
                new MapStructBeanTransformer<>(Project.class, FastJsonProject.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new MapStructBeanTransformer<>(User.class, FastJsonUser.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<PopKey, Pop, FastJsonPop> popRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPop>) template,
                new PopStringKeyFormatter(popPrefix),
                new MapStructBeanTransformer<>(Pop.class, FastJsonPop.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, TaskTypeIndicator, FastJsonTaskTypeIndicator>
    taskTypeIndicatorRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTaskTypeIndicator>) template,
                new StringIdStringKeyFormatter(taskTypeIndicatorPrefix),
                new MapStructBeanTransformer<>(
                        TaskTypeIndicator.class, FastJsonTaskTypeIndicator.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Task, FastJsonTask> taskRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTask>) template,
                new LongIdStringKeyFormatter(taskPrefix),
                new MapStructBeanTransformer<>(Task.class, FastJsonTask.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<TpKey, PreTask, FastJsonPreTask> preTaskRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPreTask>) template,
                new TpStringKeyFormatter(preTaskPrefix),
                new MapStructBeanTransformer<>(PreTask.class, FastJsonPreTask.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Memo, FastJsonMemo> memoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonMemo>) template,
                new LongIdStringKeyFormatter(memoPrefix),
                new MapStructBeanTransformer<>(Memo.class, FastJsonMemo.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, MemoFileInfo, FastJsonMemoFileInfo> memoFileInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonMemoFileInfo>) template,
                new LongIdStringKeyFormatter(memoFileInfoPrefix),
                new MapStructBeanTransformer<>(MemoFileInfo.class, FastJsonMemoFileInfo.class, FastJsonMapper.class)
        );
    }
}
