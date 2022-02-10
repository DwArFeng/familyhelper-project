package com.dwarfeng.familyhelper.project.sdk.bean.key.formatter;

import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * TpKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class TpStringKeyFormatter implements StringKeyFormatter<TpKey> {

    private String prefix;

    public TpStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(TpKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getLeftTaskId() + "_" + key.getRightTaskId();
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
        return "TpStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
