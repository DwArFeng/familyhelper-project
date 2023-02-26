package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.familyhelper.project.stack.exception.MemoRemindDriverException;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriveHandler;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriveLocalCacheHandler;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriver;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.MemoRemindDriverInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.CuratorDistributedLockHandler;
import com.dwarfeng.subgrade.impl.handler.Worker;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MemoRemindDriveHandlerImpl implements MemoRemindDriveHandler {

    private final CuratorDistributedLockHandler handler;

    public MemoRemindDriveHandlerImpl(
            CuratorFramework curatorFramework,
            @Value("${curator.latch_path.memo_remind_drive.leader_latch}") String leaserLatchPath,
            MemoRemindDriveWorker memoRemindDriveWorker
    ) {
        handler = new CuratorDistributedLockHandler(curatorFramework, leaserLatchPath, memoRemindDriveWorker);
    }

    @BehaviorAnalyse
    @Override
    public boolean isOnline() {
        return handler.isOnline();
    }

    @BehaviorAnalyse
    @Override
    public void online() throws HandlerException {
        handler.online();
    }

    @BehaviorAnalyse
    @Override
    public void offline() throws HandlerException {
        handler.offline();
    }

    @BehaviorAnalyse
    @Override
    public boolean isStarted() {
        return handler.isStarted();
    }

    @BehaviorAnalyse
    @Override
    public void start() throws HandlerException {
        handler.start();
    }

    @BehaviorAnalyse
    @Override
    public void stop() throws HandlerException {
        handler.stop();
    }

    @BehaviorAnalyse
    @Override
    public boolean isLockHolding() {
        return handler.isLockHolding();
    }

    @BehaviorAnalyse
    @Override
    public boolean isWorking() {
        return handler.isWorking();
    }

    @Component
    public static class MemoRemindDriveWorker implements Worker {

        private static final Logger LOGGER = LoggerFactory.getLogger(MemoRemindDriveWorker.class);

        private final ApplicationContext ctx;

        private final MemoRemindDriverInfoMaintainService memoRemindDriverInfoMaintainService;
        private final MemoMaintainService memoMaintainService;

        private final MemoRemindDriveLocalCacheHandler memoRemindDriveLocalCacheHandler;

        private final Set<MemoRemindDriver> usedMemoRemindDrivers = new HashSet<>();

        public MemoRemindDriveWorker(
                ApplicationContext ctx,
                MemoRemindDriverInfoMaintainService memoRemindDriverInfoMaintainService,
                MemoMaintainService memoMaintainService,
                MemoRemindDriveLocalCacheHandler memoRemindDriveLocalCacheHandler
        ) {
            this.ctx = ctx;
            this.memoRemindDriverInfoMaintainService = memoRemindDriverInfoMaintainService;
            this.memoMaintainService = memoMaintainService;
            this.memoRemindDriveLocalCacheHandler = memoRemindDriveLocalCacheHandler;
        }

        @Override
        public void work() throws Exception {
            // 记录日志。
            LOGGER.info("MemoRemindDriver驱动器开始工作...");

            List<MemoRemindDriverInfo> memoRemindDriverInfos = memoRemindDriverInfoMaintainService.lookupAsList(
                    MemoRemindDriverInfoMaintainService.REGISTRABLE, new Object[0]
            );

            // 注册所有MemoRemindDriver驱动成功标志。
            boolean successFlag = true;
            // 获取所有MemoRemindDriver驱动信息。
            for (MemoRemindDriverInfo memoRemindDriverInfo : memoRemindDriverInfos) {
                MemoRemindDriver memoRemindDriver = memoRemindDriveLocalCacheHandler.get(memoRemindDriverInfo.getKey());
                if (Objects.isNull(memoRemindDriver)) {
                    throw new MemoRemindDriverException(
                            "无法在本地缓存中找到有效的MemoRemindDriver驱动上下文: " + memoRemindDriverInfo.getKey()
                    );
                }
                if (!registerMemoRemindDriver(memoRemindDriver, memoRemindDriverInfo)) {
                    successFlag = false;
                }
            }
            if (successFlag) {
                LOGGER.info("所有MemoRemindDriver驱动信息注册成功");
            } else {
                LOGGER.warn("至少一条MemoRemindDriver驱动信息注册失败，请查看警报日志以了解详细原因");
            }
        }

        private boolean registerMemoRemindDriver(
                MemoRemindDriver memoRemindDriver, MemoRemindDriverInfo memoRemindDriverInfo
        ) {
            try {
                InternalRegisterContext context = ctx.getBean(
                        InternalRegisterContext.class, memoMaintainService, memoRemindDriverInfo
                );
                memoRemindDriver.register(context);
                usedMemoRemindDrivers.add(memoRemindDriver);
                return true;
            } catch (Exception e) {
                LOGGER.warn("MemoRemindDriver驱动信息 " + memoRemindDriverInfo + " 注册失败，将忽略此条注册信息", e);
                return false;
            }
        }

        @Override
        public void rest() throws Exception {
            // 记录日志。
            LOGGER.info("MemoRemindDriver驱动器停止工作...");

            for (Iterator<MemoRemindDriver> iterator = usedMemoRemindDrivers.iterator(); iterator.hasNext(); ) {
                MemoRemindDriver memoRemindDriver = iterator.next();
                memoRemindDriver.unregisterAll();
                iterator.remove();
            }
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class InternalRegisterContext implements MemoRemindDriver.RegisterContext {

        private final MemoMaintainService memoMaintainService;
        private final MemoRemindDriverInfo memoRemindDriverInfo;

        public InternalRegisterContext(
                MemoMaintainService memoMaintainService,
                MemoRemindDriverInfo memoRemindDriverInfo
        ) {
            this.memoMaintainService = memoMaintainService;
            this.memoRemindDriverInfo = memoRemindDriverInfo;
        }

        @Override
        public MemoRemindDriverInfo getMemoRemindDriverInfo() {
            return memoRemindDriverInfo;
        }

        @Override
        public int getStatus() throws Exception {
            Memo memo = memoMaintainService.get(memoRemindDriverInfo.getMemoKey());
            return memo.getStatus();
        }

        @Override
        public boolean isStarFlag() throws Exception {
            Memo memo = memoMaintainService.get(memoRemindDriverInfo.getMemoKey());
            return memo.isStarFlag();
        }

        @Override
        public int getPriority() throws Exception {
            Memo memo = memoMaintainService.get(memoRemindDriverInfo.getMemoKey());
            return memo.getPriority();
        }

        @Override
        public Date getExpectedFinishDate() throws Exception {
            Memo memo = memoMaintainService.get(memoRemindDriverInfo.getMemoKey());
            return memo.getExpectedFinishDate();
        }
    }
}
