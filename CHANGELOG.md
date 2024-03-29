# ChangeLog

### Release_1.2.2_20240215_build_A

#### 功能构建

- 增加 `PusherAdapter`。
  - 建议任何插件的推送器实现都继承自该适配器。
  - 适配器对所有的事件推送方法都进行了空实现。
  - 解决了增加了新的事件时，旧的推送器实现必须实现新的方法的问题。
  - 从此以后，推送器增加新的事件，将被视作兼容性更新。

- dwarfeng-ftp 优化。
  - 优化 FtpHandler 的扫描方式，使其符合最新版本标准。
  - 优化 FtpConstants 中的常量类型，使其更加符合 dwarfeng-ftp 的接口标准。

- 优化 `spring-telqos` 结构。
  - 优化指令注解。
  - 使用 `package-scan` 扫描 `telqos` 包内所有指令。

- 优化文件格式。
  - 优化 `telqos/connection.properties` 文件的格式。

- 依赖升级。
  - 升级 `snowflake` 依赖版本为 `1.5.1.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `subgrade` 依赖版本为 `1.4.8.b` 并解决兼容性问题，以应用其新功能。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.1.13.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring` 依赖版本为 `5.3.31` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.2.0` 以规避漏洞。
  - 升级 `snakeyaml` 依赖版本为 `2.0` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.22` 以规避漏洞。
  - 升级 `jetty` 依赖版本为 `9.4.51.v20230217` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.104.final` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.7.2` 以规避漏洞。
  - 升级 `guava` 依赖版本为 `32.0.1-jre` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.12.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.8.a` 以规避漏洞。

- Dubbo 微服务增加分组配置。

- 启停脚本优化。
  - 优化 Windows 系统的启动脚本。
  - 优化 Linux 系统的启停脚本。

- 为 Memo 实体增加预设查询。
  - MemoMaintainService.CHILD_FOR_USER_PROFILE_LIKE_DEFAULT_ORDER。
  - MemoMaintainService.CHILD_FOR_USER_IN_PROGRESS_PROFILE_LIKE_DEFAULT_ORDER。
  - MemoMaintainService.CHILD_FOR_USER_FINISHED_PROFILE_LIKE_DEFAULT_ORDER。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.1_20230303_build_A

#### 功能构建

- 增加 FastJson 实体。
  - com.dwarfeng.familyhelper.project.sdk.bean.dto.FastJsonMemoRemindInfo。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.0_20230227_build_A

#### 功能构建

- 优化常量类结构。

- 增加有效性验证注解。
  - com.dwarfeng.familyhelper.project.sdk.util.ValidMemoStatus。

- 增加备忘录的提醒机制。

- 增加事件推送机制。

- 增加重置机制。

- 增加运维指令。
  - com.dwarfeng.familyhelper.project.impl.service.telqos.MemoRemindDriveCommand。
  - com.dwarfeng.familyhelper.project.impl.service.telqos.MemoRemindDriveLocalCacheCommand。
  - com.dwarfeng.familyhelper.project.impl.service.telqos.ResetCommand。

- 建立实体以及维护服务，并通过单元测试。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverSupport。

- 增加实体字段。
  - Memo.expectedFinishDate。
  - Memo.brief。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.1_20230220_build_A

#### 功能构建

- 增加实体字段。
  - Memo.starFlag。
  - Memo.priority。

- 使用 `MapStruct` 重构 `BeanTransformer`。

- 增加依赖。
  - 增加依赖 `protobuf` 以规避漏洞，版本为 `3.19.6`。
  - 增加依赖 `guava` 以规避漏洞，版本为 `31.1-jre`。
  - 增加依赖 `gson` 以规避漏洞，版本为 `2.8.9`。
  - 增加依赖 `snakeyaml` 以规避漏洞，版本为 `1.33`。
  - 增加依赖 `jackson` 以规避漏洞，版本为 `2.14.0`。
  - 增加依赖 `javax.servlet-api` 以规避漏洞，版本为 `4.0.1`。
  - 增加依赖 `jboss-logging` 以规避漏洞，版本为 `3.4.3.Final`。

- 插件升级。
  - 升级 `maven-deploy-plugin` 插件版本为 `2.8.2`。

- 依赖升级。
  - 升级 `mysql` 依赖版本为 `8.0.31` 以规避漏洞。
  - 升级 `jedis` 依赖版本为 `3.8.0` 以规避漏洞。
  - 升级 `spring-data-redis` 依赖版本为 `2.7.5` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.18` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.5.7` 以规避漏洞。
  - 升级 `curator` 依赖版本为 `4.3.0` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.2.5.Final` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.2.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.10.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.3.0.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.1.2.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.10.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.5.a` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.0_20220702_build_A

#### 功能构建

- 依赖升级。
  - 升级 `junit` 依赖版本为 `4.13.2` 以规避漏洞。
  - 升级 `spring` 依赖版本为 `5.3.20` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.0.28` 以规避漏洞。
  - 升级 `fastjson` 依赖版本为 `1.2.83` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.15` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.77.Final` 以规避漏洞。
  - 升级 `hibernate` 依赖版本为 `5.3.20.Final` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.0.21.Final` 以规避漏洞。
  - 升级 `hibernate` 依赖版本为 `5.3.20.Final` 以规避漏洞。
  - 升级 `log4j2` 依赖版本为 `2.17.2` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.0.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.7.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.8.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.8.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.3.a` 以规避漏洞。

#### Bug修复

- 修正 `WebInputTaskUpdateInfo` 的 JSON 格式。

#### 功能移除

- 删除实体。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.TimePoint。

- 删除不需要的依赖。
  - 删除 `joda-time` 依赖。
  - 删除 `httpcomponents` 依赖。

---

### Release_1.0.1_20220325_build_A

#### 功能构建

- 增加备忘录服务的预设查询。

- 优化操作服务验证环节的代码结构。

- 增加备忘录操作服务的方法。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.0_20220309_build_A

#### 功能构建

- 项目结构建立，程序清理测试通过。

- 建立实体以及维护服务，并通过单元测试。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.Project。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.User。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.Pop。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.TaskTypeIndicator。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.Task。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.Memo。
  - com.dwarfeng.familyhelper.project.stack.bean.entity.MemoFileInfo。

- 完成 node 模块，打包测试及启动测试通过。

- 完成实体的操作服务。
  - com.dwarfeng.familyhelper.project.stack.service.ProjectOperateService。
  - com.dwarfeng.familyhelper.project.stack.service.TaskOperateService。
  - com.dwarfeng.familyhelper.project.stack.service.MemoOperateService。
  - com.dwarfeng.familyhelper.project.stack.service.MemoFileOperateService。

#### Bug修复

- (无)

#### 功能移除

- (无)
