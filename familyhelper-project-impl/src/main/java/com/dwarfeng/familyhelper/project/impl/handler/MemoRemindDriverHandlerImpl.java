package com.dwarfeng.familyhelper.project.impl.handler;

import com.dwarfeng.familyhelper.project.stack.exception.UnsupportedMemoRemindDriverTypeException;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriver;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindDriverHandler;
import com.dwarfeng.familyhelper.project.stack.handler.MemoRemindHandler;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class MemoRemindDriverHandlerImpl implements MemoRemindDriverHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoRemindDriverHandlerImpl.class);

    private final MemoRemindHandler memoRemindHandler;

    private final List<MemoRemindDriverProvider> memoRemindDriverProviders;

    private final InternalDriverContext memoRemindDriverContext = new InternalDriverContext();

    public MemoRemindDriverHandlerImpl(
            MemoRemindHandler memoRemindHandler, List<MemoRemindDriverProvider> memoRemindDriverProviders
    ) {
        this.memoRemindHandler = memoRemindHandler;
        this.memoRemindDriverProviders = Objects.isNull(memoRemindDriverProviders) ?
                Collections.emptyList() : memoRemindDriverProviders;
    }

    @PostConstruct
    public void init() {
        LOGGER.info("初始化驱动器...");
        memoRemindDriverProviders.stream().map(MemoRemindDriverProvider::provide).forEach(
                driver -> driver.init(memoRemindDriverContext)
        );
    }

    @Override
    public MemoRemindDriver find(String type) throws HandlerException {
        return memoRemindDriverProviders.stream()
                .filter(mrdp -> mrdp.supportType(type)).map(MemoRemindDriverProvider::provide)
                .findAny().orElseThrow(() -> new UnsupportedMemoRemindDriverTypeException(type));
    }

    private class InternalDriverContext implements MemoRemindDriver.DriverContext {

        @Override
        public void remind(LongIdKey memoRemindDriverInfoKey) throws Exception {
            memoRemindHandler.remind(memoRemindDriverInfoKey);
        }
    }
}
