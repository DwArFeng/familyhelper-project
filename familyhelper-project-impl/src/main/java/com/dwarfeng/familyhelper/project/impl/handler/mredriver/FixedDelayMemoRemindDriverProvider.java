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
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定间隔备忘录提醒驱动提供器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Component
public class FixedDelayMemoRemindDriverProvider implements MemoRemindDriverProvider {

    public static final String SUPPORT_TYPE = "fixed_delay_memo_remind_driver";

    private final FixedDelayMemoRemindDriver fixedDelayDriver;

    public FixedDelayMemoRemindDriverProvider(FixedDelayMemoRemindDriver fixedDelayDriver) {
        this.fixedDelayDriver = fixedDelayDriver;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public MemoRemindDriver provide() {
        return fixedDelayDriver;
    }

    @Component
    public static class FixedDelayMemoRemindDriver extends AbstractMemoRemindDriver {

        private final ApplicationContext ctx;

        private final ThreadPoolTaskScheduler scheduler;

        private final Lock lock = new ReentrantLock();
        private final Set<ScheduledFuture<?>> scheduledFutures = new HashSet<>();
        private final Set<InternalProcessor> internalProcessors = new HashSet<>();

        public FixedDelayMemoRemindDriver(ApplicationContext ctx, ThreadPoolTaskScheduler scheduler) {
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
                FixedDelayMemoRemindDriverConfig config = JSON.parseObject(
                        memoRemindDriverInfo.getParam(), FixedDelayMemoRemindDriverConfig.class
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
            return "FixedDelayMemoRemindDriver{" +
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
                String message = "计划时间已到达, fixed delay 备忘录提醒驱动器 " + memoRemindDriverInfoKey +
                        " 执行备忘录提醒动作...";
                LOGGER.debug(message);
                context.remind(memoRemindDriverInfoKey);
            } catch (Exception e) {
                String message = "Fixed delay 备忘录提醒驱动器 " + memoRemindDriverInfoKey +
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
        private final FixedDelayMemoRemindDriverConfig config;

        private long delay;
        private int timeRangeType;

        public InternalTrigger(MemoRemindDriver.RegisterContext context, FixedDelayMemoRemindDriverConfig config) {
            this.context = context;
            this.config = config;
        }

        @PostConstruct
        public void init() {
            delay = config.getDelay();
            timeRangeType = config.getTimeRangeType();
        }

        // 为了代码的可读性，不简化代码。
        @SuppressWarnings({"DuplicateBranchesInSwitch", "DuplicatedCode"})
        @Override
        public Date nextExecutionTime(@Nonnull TriggerContext triggerContext) {
            try {
                int status = context.getStatus();
                Date expectedFinishDate = context.getExpectedFinishDate();

                // 状态判断: 如果备忘录已经完成了，则不进行任何提示。
                if (Objects.equals(status, Constants.MEMO_STATUS_FINISHED)) {
                    return null;
                }

                // 预期完成日期空值判断: 如果未指定预期完成日期，则按照全时间范围进行判断。
                if (Objects.isNull(expectedFinishDate)) {
                    return nextAll(triggerContext);
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
            Date date = triggerContext.lastCompletionTime();
            if (Objects.isNull(date)) {
                return new Date(triggerContext.getClock().millis());
            }
            return new Date(date.getTime() + delay);
        }

        private Date nextBefore(TriggerContext triggerContext, Date expectedFinishDate) {
            Date date = triggerContext.lastCompletionTime();
            Date nextDate;
            if (Objects.isNull(date)) {
                nextDate = new Date(triggerContext.getClock().millis());
            } else {
                nextDate = new Date(date.getTime() + delay);
            }
            if (nextDate.getTime() <= expectedFinishDate.getTime()) {
                return nextDate;
            } else {
                return null;
            }
        }

        @SuppressWarnings("DuplicatedCode")
        private Date nextAfter(TriggerContext triggerContext, Date expectedFinishDate) {
            Date date = triggerContext.lastCompletionTime();
            Date nextDate;
            if (Objects.isNull(date)) {
                nextDate = new Date(triggerContext.getClock().millis());
            } else {
                nextDate = new Date(date.getTime() + delay);
            }
            long nextDateTime = nextDate.getTime();
            long expectedFinishDateTime = expectedFinishDate.getTime();
            if (nextDateTime < expectedFinishDateTime) {
                long times = (expectedFinishDateTime - nextDateTime) / delay;
                nextDateTime += delay * times;
                if (nextDateTime < expectedFinishDateTime) {
                    nextDateTime += delay;
                }
                nextDate = new Date(nextDateTime);
            }
            return nextDate;
        }
    }
}
