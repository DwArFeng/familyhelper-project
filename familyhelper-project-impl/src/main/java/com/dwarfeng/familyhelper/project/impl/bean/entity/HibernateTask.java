package com.dwarfeng.familyhelper.project.impl.bean.entity;

import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_task")
public class HibernateTask implements Bean {

    private static final long serialVersionUID = -903785545165264901L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "project_id")
    private Long projectLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "type")
    private String type;

    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "finished_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedDate;

    @Column(name = "total_mission_count")
    private int totalMissionCount;

    @Column(name = "finished_mission_count")
    private int finishedMissionCount;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateProject.class)
    @JoinColumns({ //
            @JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateProject project;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernatePreTask.class, mappedBy = "subjectTask")
    private Set<HibernatePreTask> subjectPreTasks = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernatePreTask.class, mappedBy = "objectTask")
    private Set<HibernatePreTask> objectPreTasks = new HashSet<>();

    public HibernateTask() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getProjectKey() {
        return Optional.ofNullable(projectLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setProjectKey(HibernateLongIdKey key) {
        this.projectLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getProjectLongId() {
        return projectLongId;
    }

    public void setProjectLongId(Long projectLongId) {
        this.projectLongId = projectLongId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getTotalMissionCount() {
        return totalMissionCount;
    }

    public void setTotalMissionCount(int totalMissionCount) {
        this.totalMissionCount = totalMissionCount;
    }

    public int getFinishedMissionCount() {
        return finishedMissionCount;
    }

    public void setFinishedMissionCount(int finishedMissionCount) {
        this.finishedMissionCount = finishedMissionCount;
    }

    public HibernateProject getProject() {
        return project;
    }

    public void setProject(HibernateProject project) {
        this.project = project;
    }

    public Set<HibernatePreTask> getSubjectPreTasks() {
        return subjectPreTasks;
    }

    public void setSubjectPreTasks(Set<HibernatePreTask> subjectPreTasks) {
        this.subjectPreTasks = subjectPreTasks;
    }

    public Set<HibernatePreTask> getObjectPreTasks() {
        return objectPreTasks;
    }

    public void setObjectPreTasks(Set<HibernatePreTask> objectPreTasks) {
        this.objectPreTasks = objectPreTasks;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "projectLongId = " + projectLongId + ", " +
                "type = " + type + ", " +
                "name = " + name + ", " +
                "remark = " + remark + ", " +
                "status = " + status + ", " +
                "createdDate = " + createdDate + ", " +
                "modifiedDate = " + modifiedDate + ", " +
                "finishedDate = " + finishedDate + ", " +
                "totalMissionCount = " + totalMissionCount + ", " +
                "finishedMissionCount = " + finishedMissionCount + ", " +
                "project = " + project + ")";
    }
}
