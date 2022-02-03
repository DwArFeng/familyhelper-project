package com.dwarfeng.familyhelper.project.sdk.bean.key.formatter;

import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * PopKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class PopStringKeyFormatter implements StringKeyFormatter<PopKey> {

    private String prefix;

    public PopStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(PopKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getLongId() + "_" + key.getStringId();
    }

    @Override
    public String generalFormat() {
        return prefix + Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "PopStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
