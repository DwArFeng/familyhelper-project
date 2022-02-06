package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 工程更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ProjectUpdateInfo implements Dto {

    private static final long serialVersionUID = -3401382528157203885L;

    private LongIdKey projectKey;
    private String name;
    private String remark;
    private int status;

    public ProjectUpdateInfo() {
    }

    public ProjectUpdateInfo(LongIdKey projectKey, String name, String remark, int status) {
        this.projectKey = projectKey;
        this.name = name;
        this.remark = remark;
        this.status = status;
    }

    public LongIdKey getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(LongIdKey projectKey) {
        this.projectKey = projectKey;
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
        return "ProjectUpdateInfo{" +
                "projectKey=" + projectKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }
}
