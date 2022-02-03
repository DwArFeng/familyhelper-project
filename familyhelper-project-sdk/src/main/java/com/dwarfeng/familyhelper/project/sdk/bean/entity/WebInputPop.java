package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.bean.key.WebInputPopKey;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 工程摘要。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputPop implements Bean {

    private static final long serialVersionUID = 3506363410896643083L;

    public static Pop toStackBean(WebInputPop webInputPop) {
        if (Objects.isNull(webInputPop)) {
            return null;
        } else {
            return new Pop(
                    WebInputPopKey.toStackBean(webInputPop.getKey()),
                    webInputPop.getPermissionLevel(),
                    webInputPop.isStar(),
                    webInputPop.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputPopKey key;

    @JSONField(name = "permission_level")
    private int permissionLevel;

    @JSONField(name = "star")
    private boolean star;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputPop() {
    }

    public WebInputPopKey getKey() {
        return key;
    }

    public void setKey(WebInputPopKey key) {
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
        return "WebInputPop{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", star=" + star +
                ", remark='" + remark + '\'' +
                '}';
    }
}
