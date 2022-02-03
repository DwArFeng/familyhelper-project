package com.dwarfeng.familyhelper.project.stack.bean.entity;

import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 工程摘要。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Pop implements Entity<PopKey> {

    private static final long serialVersionUID = -4249189103880968218L;

    private PopKey key;
    private int permissionLevel;
    private boolean star;
    private String remark;

    public Pop() {
    }

    public Pop(PopKey key, int permissionLevel, boolean star, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.star = star;
        this.remark = remark;
    }

    @Override
    public PopKey getKey() {
        return key;
    }

    @Override
    public void setKey(PopKey key) {
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
        return "Pop{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", star=" + star +
                ", remark='" + remark + '\'' +
                '}';
    }
}
