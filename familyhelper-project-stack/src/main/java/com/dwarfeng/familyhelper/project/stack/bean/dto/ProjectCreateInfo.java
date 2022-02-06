package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 工程创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ProjectCreateInfo implements Dto {

    private static final long serialVersionUID = 6202309460057597780L;

    private String name;
    private String remark;
    private int status;

    public ProjectCreateInfo() {
    }

    public ProjectCreateInfo(String name, String remark, int status) {
        this.name = name;
        this.remark = remark;
        this.status = status;
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
        return "ProjectCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }
}
