package com.dwarfeng.familyhelper.project.impl.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.familyhelper.project.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        //实体对象。
        ParserConfig.getGlobalInstance().addAccept(FastJsonProject.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonUser.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonPop.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonTaskTypeIndicator.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonTask.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonPreTask.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonMemo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonMemoFileInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonMemoRemindDriverInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonMemoRemindDriverSupport.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
