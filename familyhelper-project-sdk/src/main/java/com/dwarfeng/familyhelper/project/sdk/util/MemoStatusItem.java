package com.dwarfeng.familyhelper.project.sdk.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 备忘录状态条目。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface MemoStatusItem {
}
