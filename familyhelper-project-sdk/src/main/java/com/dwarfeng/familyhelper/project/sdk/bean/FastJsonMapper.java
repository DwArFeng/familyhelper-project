package com.dwarfeng.familyhelper.project.sdk.bean;

import com.dwarfeng.familyhelper.project.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.project.sdk.bean.key.FastJsonPopKey;
import com.dwarfeng.familyhelper.project.sdk.bean.key.FastJsonTpKey;
import com.dwarfeng.familyhelper.project.stack.bean.entity.*;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Mapper
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonPopKey popKeyToFastJson(PopKey popKey);

    @InheritInverseConfiguration
    PopKey popKeyFromFastJson(FastJsonPopKey fastJsonPopKey);

    FastJsonTpKey tpKeyToFastJson(TpKey tpKey);

    @InheritInverseConfiguration
    TpKey tpKeyFromFastJson(FastJsonTpKey fastJsonTpKey);

    FastJsonMemo memoToFastJson(Memo memo);

    @InheritInverseConfiguration
    Memo memoFromFastJson(FastJsonMemo fastJsonMemo);

    FastJsonMemoFileInfo memoFileInfoToFastJson(MemoFileInfo memoFileInfo);

    @InheritInverseConfiguration
    MemoFileInfo memoFileInfoFromFastJson(FastJsonMemoFileInfo fastJsonMemoFileInfo);

    FastJsonPop popToFastJson(Pop pop);

    @InheritInverseConfiguration
    Pop popFromFastJson(FastJsonPop fastJsonPop);

    FastJsonPreTask preTaskToFastJson(PreTask preTask);

    @InheritInverseConfiguration
    PreTask preTaskFromFastJson(FastJsonPreTask fastJsonPreTask);

    FastJsonProject projectToFastJson(Project project);

    @InheritInverseConfiguration
    Project projectFromFastJson(FastJsonProject fastJsonProject);

    FastJsonTask taskToFastJson(Task task);

    @InheritInverseConfiguration
    Task taskFromFastJson(FastJsonTask fastJsonTask);

    FastJsonTaskTypeIndicator taskTypeIndicatorToFastJson(TaskTypeIndicator taskTypeIndicator);

    @InheritInverseConfiguration
    TaskTypeIndicator taskTypeIndicatorFromFastJson(FastJsonTaskTypeIndicator fastJsonTaskTypeIndicator);

    FastJsonUser userToFastJson(User user);

    @InheritInverseConfiguration
    User userFromFastJson(FastJsonUser fastJsonUser);
}
