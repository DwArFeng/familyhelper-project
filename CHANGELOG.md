# ChangeLog

### Release_1.1.0_20220404_build_A

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
