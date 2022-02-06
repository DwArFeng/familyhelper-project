package com.dwarfeng.familyhelper.project.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.dto.ProjectCreateInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 工程创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputProjectCreateInfo implements Dto {

    private static final long serialVersionUID = 9139246979264142854L;

    public static ProjectCreateInfo toStackBean(WebInputProjectCreateInfo webInputProjectCreateInfo) {
        if (Objects.isNull(webInputProjectCreateInfo)) {
            return null;
        } else {
            return new ProjectCreateInfo(
                    webInputProjectCreateInfo.getName(), webInputProjectCreateInfo.getRemark(),
                    webInputProjectCreateInfo.getStatus()
            );
        }
    }

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "status")
    private int status;

    public WebInputProjectCreateInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WebInputProjectCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }
}
