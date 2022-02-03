package com.dwarfeng.familyhelper.project.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 工程摘要主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputPopKey implements Key {

    private static final long serialVersionUID = 6043590056488163201L;

    public static PopKey toStackBean(WebInputPopKey webInputPopKey) {
        if (Objects.isNull(webInputPopKey)) {
            return null;
        } else {
            return new PopKey(webInputPopKey.getLongId(), webInputPopKey.getStringId());
        }
    }

    @JSONField(name = "long_id")
    @NotNull
    private Long longId;

    @JSONField(name = "string_id")
    @NotNull
    @NotEmpty
    private String stringId;

    public WebInputPopKey() {
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    @Override
    public String toString() {
        return "WebInputPopKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
