package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 工程。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputProject implements Bean {

    private static final long serialVersionUID = -4473593833029384643L;

    public static Project toStackBean(WebInputProject webInputProject) {
        if (Objects.isNull(webInputProject)) {
            return null;
        } else {
            return new Project(
                    WebInputLongIdKey.toStackBean(webInputProject.getKey()),
                    webInputProject.getName(), webInputProject.getRemark(), webInputProject.getStatus(),
                    webInputProject.getCreatedDate(), webInputProject.getModifiedDate(),
                    webInputProject.getFinishedDate()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "status")
    private int status;

    @JSONField(name = "created_date")
    private Date createdDate;

    @JSONField(name = "modified_date")
    private Date modifiedDate;

    @JSONField(name = "finished_date")
    private Date finishedDate;

    public WebInputProject() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    @Override
    public String toString() {
        return "WebInputProject{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", finishedDate=" + finishedDate +
                '}';
    }
}
