package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.bean.key.FastJsonPopKey;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 工程摘要。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonPop implements Bean {

    private static final long serialVersionUID = -4921174625429212657L;

    public static FastJsonPop of(Pop pop) {
        if (Objects.isNull(pop)) {
            return null;
        } else {
            return new FastJsonPop(
                    FastJsonPopKey.of(pop.getKey()),
                    pop.getPermissionLevel(), pop.isStar(), pop.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonPopKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "star", ordinal = 3)
    private boolean star;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonPop() {
    }

    public FastJsonPop(FastJsonPopKey key, int permissionLevel, boolean star, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.star = star;
        this.remark = remark;
    }

    public FastJsonPopKey getKey() {
        return key;
    }

    public void setKey(FastJsonPopKey key) {
        this.key = key;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonPop{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", star=" + star +
                ", remark='" + remark + '\'' +
                '}';
    }
}
