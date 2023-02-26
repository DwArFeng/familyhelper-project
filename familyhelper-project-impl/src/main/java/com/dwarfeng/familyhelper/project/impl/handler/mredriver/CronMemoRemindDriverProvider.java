package com.dwarfeng.familyhelper.project.impl.handler.mredriver;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.familyhelper.project.impl.handler.MemoRemindDriverProvider;
import com.dwarfeng.familyhelper.project.sdk.util.Constants;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.familyhelper.project.stack.exception.MemoRemindDriverException;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriver;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Cron备忘录提醒驱动提供器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Component
public class CronMemoRemindDriverProvider implements MemoRemindDriverProvider {

    public static final String SUPPORT_TYPE = "cron_memo_remind_driver";

    private final CronMemoRemindDriver cronDriver;

    public CronMemoRemindDriverProvider(CronMemoRemindDriver cronDriver) {
        this.cronDriver = cronDriver;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public MemoRemindDriver provide() {
        return cronDriver;
    }

    @Component
    public static class CronMemoRemindDriver extends AbstractMemoRemindDriver {

        private final ApplicationContext ctx;

        private final ThreadPoolTaskScheduler scheduler;

        private final Lock lock = new ReentrantLock();
        private final Set<ScheduledFuture<?>> scheduledFutures = new HashSet<>();
        private final Set<InternalProcessor> internalProcessors = new HashSet<>();

        public CronMemoRemindDriver(ApplicationContext ctx, ThreadPoolTaskScheduler scheduler) {
            this.ctx = ctx;
            this.scheduler = scheduler;
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        public void register(RegisterContext registerContext) throws MemoRemindDriverException {
            lock.lock();
            try {
                MemoRemindDriverInfo memoRemindDriverInfo = registerContext.getMemoRemindDriverInfo();
                LongIdKey memoRemindDriverInfoKey = memoRemindDriverInfo.getKey();
                CronMemoRemindDriverConfig config = JSON.parseObject(
                        memoRemindDriverInfo.getParam(), CronMemoRemindDriverConfig.class
                );
                InternalProcessor internalProcessor = ctx.getBean(
                        InternalProcessor.class, driverContext, memoRemindDriverInfoKey
                );
                InternalTrigger internalTrigger = ctx.getBean(InternalTrigger.class, registerContext, config);
                ScheduledFuture<?> scheduledFuture = scheduler.schedule(internalProcessor, internalTrigger);
                internalProcessors.add(internalProcessor);
                scheduledFutures.add(scheduledFuture);
            } catch (Exception e) {
                throw new MemoRemindDriverException(e);
            } finally {
                lock.unlock();
            }
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        public void unregisterAll() {
            lock.lock();
            try {
                for (ScheduledFuture<?> scheduledFuture : scheduledFutures) {
                    scheduledFuture.cancel(false);
                }
                for (InternalProcessor internalProcessor : internalProcessors) {
                    internalProcessor.shutdown();
                }
            } finally {
                lock.unlock();
            }
        }

        @Override
        public String toString() {
            return "CronMemoRemindDriver{" +
                    "driverContext=" + driverContext +
                    '}';
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class InternalProcessor implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(InternalProcessor.class);

        private final MemoRemindDriver.DriverContext context;
        private final LongIdKey memoRemindDriverInfoKey;

        private final Lock lock = new ReentrantLock();
        private boolean runningFlag = true;

        public InternalProcessor(MemoRemindDriver.DriverContext context, LongIdKey memoRemindDriverInfoKey) {
            this.context = context;
            this.memoRemindDriverInfoKey = memoRemindDriverInfoKey;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                if (!runningFlag) {
                    return;
                }

                String message = "计划时间已到达, cron 备忘录提醒驱动器 " + memoRemindDriverInfoKey +
                        " 执行备忘录提醒动作...";
                LOGGER.debug(message);
                context.remind(memoRemindDriverInfoKey);
            } catch (Exception e) {
                String message = "Cron 备忘录提醒驱动器 " + memoRemindDriverInfoKey +
                        " 执行备忘录提醒动作时出现异常, 放弃本次备忘录提醒";
                LOGGER.warn(message, e);
            } finally {
                lock.unlock();
            }
        }

        void shutdown() {
            lock.lock();
            try {
                runningFlag = false;
            } finally {
                lock.unlock();
            }
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class InternalTrigger implements Trigger {

        private static final Logger LOGGER = LoggerFactory.getLogger(InternalTrigger.class);

        private final MemoRemindDriver.RegisterContext context;
        private final CronMemoRemindDriverConfig config;

        private CronExpression cronExpression;
        private int timeRangeType;

        public InternalTrigger(MemoRemindDriver.RegisterContext context, CronMemoRemindDriverConfig config) {
            this.context = context;
            this.config = config;
        }

        @PostConstruct
        public void init() {
            cronExpression = CronExpression.parse(config.getCron());
            timeRangeType = config.getTimeRangeType();
        }

        // 为了代码的可读性，不简化代码。
        @SuppressWarnings({"DuplicateBranchesInSwitch", "DuplicatedCode"})
        @Override
        public Date nextExecutionTime(@Nonnull TriggerContext triggerContext) {
            try {
                int status = context.getStatus();
                Date expectedFinishDate = context.getExpectedFinishDate();
                if (Objects.equals(status, Constants.MEMO_STATUS_FINISHED)) {
                    return null;
                }

                switch (timeRangeType) {
                    case CronMemoRemindDriverConfig.TIME_RANGE_TYPE_ALL:
                        return nextAll(triggerContext);
                    case CronMemoRemindDriverConfig.TIME_RANGE_TYPE_BEFORE_EXCEPTED_FINISH_DATE:
                        return nextBefore(triggerContext, expectedFinishDate);
                    case CronMemoRemindDriverConfig.TIME_RANGE_TYPE_AFTER_EXCEPTED_FINISH_DATE:
                        return nextAfter(triggerContext, expectedFinishDate);
                    default:
                        return nextAll(triggerContext);
                }
            } catch (Exception e) {
                String message = "本触发器获取下一次执行时间时发生异常, 直到备忘录提醒重置前将不会继续参与触发, " +
                        "异常信息如下: ";
                LOGGER.warn(message, e);
                return null;
            }
        }

        private Date nextAll(TriggerContext triggerContext) {
            Date date = getLastCompletionTime(triggerContext);
            ZonedDateTime dateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            ZonedDateTime next = cronExpression.next(dateTime);
            return (next != null ? Date.from(next.toInstant()) : null);
        }

        private Date nextBefore(TriggerContext triggerContext, Date expectedFinishDate) {
            Date date = getLastCompletionTime(triggerContext);
            ZonedDateTime dateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            ZonedDateTime next = cronExpression.next(dateTime);

            if (Objects.isNull(next)) {
                return null;
            }

            Date cronDate = Date.from(next.toInstant());
            if (cronDate.getTime() <= expectedFinishDate.getTime()) {
                return cronDate;
            } else {
                return null;
            }
        }

        private Date nextAfter(TriggerContext triggerContext, Date expectedFinishDate) {
            Date date = getLastCompletionTime(triggerContext);
            if (date.getTime() < expectedFinishDate.getTime()) {
                date = expectedFinishDate;
            }
            ZonedDateTime dateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            ZonedDateTime next = cronExpression.next(dateTime);
            return (next != null ? Date.from(next.toInstant()) : null);
        }

        @SuppressWarnings("DuplicatedCode")
        private Date getLastCompletionTime(TriggerContext triggerContext) {
            Date date = triggerContext.lastCompletionTime();
            if (date != null) {
                Date scheduled = triggerContext.lastScheduledExecutionTime();
                if (scheduled != null && date.before(scheduled)) {
                    // Previous task apparently executed too early...
                    // Let's simply use the last calculated execution time then,
                    // in order to prevent accidental re-fires in the same second.
                    date = scheduled;
                }
            } else {
                date = new Date(triggerContext.getClock().millis());
            }
            return date;
        }
    }
}
