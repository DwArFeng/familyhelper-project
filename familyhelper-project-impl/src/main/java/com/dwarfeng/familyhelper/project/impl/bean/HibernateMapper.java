package com.dwarfeng.familyhelper.project.impl.bean;

import com.dwarfeng.familyhelper.project.impl.bean.entity.*;
import com.dwarfeng.familyhelper.project.impl.bean.key.HibernatePopKey;
import com.dwarfeng.familyhelper.project.impl.bean.key.HibernateTpKey;
import com.dwarfeng.familyhelper.project.stack.bean.entity.*;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Hibernate Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Mapper
public interface HibernateMapper {

    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    HibernatePopKey popKeyToHibernate(PopKey popKey);

    @InheritInverseConfiguration
    PopKey popKeyFromHibernate(HibernatePopKey hibernatePopKey);

    HibernateTpKey tpKeyToHibernate(TpKey tpKey);

    @InheritInverseConfiguration
    TpKey tpKeyFromHibernate(HibernateTpKey hibernateTpKey);

    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "memoFileInfos", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateMemo memoToHibernate(Memo memo);

    @InheritInverseConfiguration
    Memo memoFromHibernate(HibernateMemo hibernateMemo);

    @Mapping(target = "memoLongId", ignore = true)
    @Mapping(target = "memo", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateMemoFileInfo memoFileInfoToHibernate(MemoFileInfo memoFileInfo);

    @InheritInverseConfiguration
    MemoFileInfo memoFileInfoFromHibernate(HibernateMemoFileInfo hibernateMemoFileInfo);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernatePop popToHibernate(Pop pop);

    @InheritInverseConfiguration
    Pop popFromHibernate(HibernatePop hibernatePop);

    @Mapping(target = "subjectTask", ignore = true)
    @Mapping(target = "rightTaskId", ignore = true)
    @Mapping(target = "objectTask", ignore = true)
    @Mapping(target = "leftTaskId", ignore = true)
    HibernatePreTask preTaskToHibernate(PreTask preTask);

    @InheritInverseConfiguration
    PreTask preTaskFromHibernate(HibernatePreTask hibernatePreTask);

    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "pops", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateProject projectToHibernate(Project project);

    @InheritInverseConfiguration
    Project projectFromHibernate(HibernateProject hibernateProject);

    @Mapping(target = "subjectPreTasks", ignore = true)
    @Mapping(target = "projectLongId", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "objectPreTasks", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateTask taskToHibernate(Task task);

    @InheritInverseConfiguration
    Task taskFromHibernate(HibernateTask hibernateTask);

    @Mapping(target = "stringId", ignore = true)
    HibernateTaskTypeIndicator taskTypeIndicatorToHibernate(TaskTypeIndicator taskTypeIndicator);

    @InheritInverseConfiguration
    TaskTypeIndicator taskTypeIndicatorFromHibernate(HibernateTaskTypeIndicator hibernateTaskTypeIndicator);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "pops", ignore = true)
    @Mapping(target = "memos", ignore = true)
    HibernateUser userToHibernate(User user);

    @InheritInverseConfiguration
    User userFromHibernate(HibernateUser hibernateUser);
}
