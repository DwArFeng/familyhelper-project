package com.dwarfeng.familyhelper.project.impl.bean.entity;

import com.dwarfeng.familyhelper.project.impl.bean.key.HibernatePopKey;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernatePopKey.class)
@Table(name = "tbl_pop")
public class HibernatePop implements Bean {

    private static final long serialVersionUID = -3903011548308940766L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "long_id", nullable = false)
    private Long longId;

    @Id
    @Column(name = "string_id", length = Constraints.LENGTH_USER, nullable = false)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "permission_level")
    private int permissionLevel;

    @Column(name = "star")
    private boolean star;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateProject.class)
    @JoinColumns({ //
            @JoinColumn(name = "long_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateProject project;

    @ManyToOne(targetEntity = HibernateUser.class)
    @JoinColumns({ //
            @JoinColumn(name = "string_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateUser user;


    public HibernatePop() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernatePopKey getKey() {
        return new HibernatePopKey(longId, stringId);
    }

    public void setKey(HibernatePopKey key) {
        if (Objects.isNull(key)) {
            this.longId = null;
            this.stringId = null;
        } else {
            this.longId = key.getLongId();
            this.stringId = key.getStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
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

    public HibernateProject getProject() {
        return project;
    }

    public void setProject(HibernateProject project) {
        this.project = project;
    }

    public HibernateUser getUser() {
        return user;
    }

    public void setUser(HibernateUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "stringId = " + stringId + ", " +
                "permissionLevel = " + permissionLevel + ", " +
                "star = " + star + ", " +
                "remark = " + remark + ", " +
                "project = " + project + ", " +
                "user = " + user + ")";
    }
}
