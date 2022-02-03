package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 工程。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonProject implements Bean {

    private static final long serialVersionUID = -289812324615847658L;

    public static FastJsonProject of(Project project) {
        if (Objects.isNull(project)) {
            return null;
        } else {
            return new FastJsonProject(
                    FastJsonLongIdKey.of(project.getKey()),
                    project.getName(), project.getRemark(), project.getStatus(), project.getCreatedDate(),
                    project.getModifiedDate(), project.getFinishedDate()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "status", ordinal = 4)
    private int status;

    @JSONField(name = "created_date", ordinal = 5)
    private Date createdDate;

    @JSONField(name = "modified_date", ordinal = 6)
    private Date modifiedDate;

    @JSONField(name = "finished_date", ordinal = 7)
    private Date finishedDate;

    public FastJsonProject() {
    }

    public FastJsonProject(
            FastJsonLongIdKey key, String name, String remark, int status, Date createdDate, Date modifiedDate,
            Date finishedDate
    ) {
        this.key = key;
        this.name = name;
        this.remark = remark;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.finishedDate = finishedDate;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
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
        return "FastJsonProject{" +
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
