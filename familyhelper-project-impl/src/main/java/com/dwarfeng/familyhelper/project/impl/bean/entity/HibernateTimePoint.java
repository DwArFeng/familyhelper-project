package com.dwarfeng.familyhelper.project.impl.bean.entity;

import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_time_point")
public class HibernateTimePoint implements Bean {

    private static final long serialVersionUID = -2016949801717105705L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "task_id")
    private Long taskLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "profile", length = Constraints.LENGTH_PROFILE, nullable = false)
    private String profile;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "expected_finished_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expectedFinishedDate;

    @Column(name = "actual_finished_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualFinishedDate;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateTask.class)
    @JoinColumns({ //
            @JoinColumn(name = "task_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTask task;

    public HibernateTimePoint() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getTaskKey() {
        return Optional.ofNullable(taskLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setTaskKey(HibernateLongIdKey key) {
        this.taskLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getTaskLongId() {
        return taskLongId;
    }

    public void setTaskLongId(Long taskLongId) {
        this.taskLongId = taskLongId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    public Date getExpectedFinishedDate() {
        return expectedFinishedDate;
    }

    public void setExpectedFinishedDate(Date expectedFinishedDate) {
        this.expectedFinishedDate = expectedFinishedDate;
    }

    public Date getActualFinishedDate() {
        return actualFinishedDate;
    }

    public void setActualFinishedDate(Date actualFinishedDate) {
        this.actualFinishedDate = actualFinishedDate;
    }

    public HibernateTask getTask() {
        return task;
    }

    public void setTask(HibernateTask task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "taskLongId = " + taskLongId + ", " +
                "profile = " + profile + ", " +
                "remark = " + remark + ", " +
                "status = " + status + ", " +
                "expectedFinishedDate = " + expectedFinishedDate + ", " +
                "actualFinishedDate = " + actualFinishedDate + ", " +
                "task = " + task + ")";
    }
}