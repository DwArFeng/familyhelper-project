package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.bean.key.JSFixedFastJsonPopKey;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 工程权限。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonPop implements Bean {

    private static final long serialVersionUID = 2488413790306158951L;

    public static JSFixedFastJsonPop of(Pop pop) {
        if (Objects.isNull(pop)) {
            return null;
        } else {
            return new JSFixedFastJsonPop(
                    JSFixedFastJsonPopKey.of(pop.getKey()),
                    pop.getPermissionLevel(), pop.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonPopKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonPop() {
    }

    public JSFixedFastJsonPop(JSFixedFastJsonPopKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    public JSFixedFastJsonPopKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonPopKey key) {
        this.key = key;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonPop{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
