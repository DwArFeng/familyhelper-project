package com.dwarfeng.familyhelper.project.impl.bean.entity;

import com.dwarfeng.familyhelper.project.impl.bean.key.HibernateTpKey;
import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateTpKey.class)
@Table(name = "tbl_pre_task")
public class HibernatePreTask implements Bean {

    private static final long serialVersionUID = -7693391953213989472L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "left_task_id", nullable = false)
    private Long leftTaskId;

    @Id
    @Column(name = "right_task_id", nullable = false)
    private Long rightTaskId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateTask.class)
    @JoinColumns({ //
            @JoinColumn(name = "left_task_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTask subjectTask;

    @ManyToOne(targetEntity = HibernateTask.class)
    @JoinColumns({ //
            @JoinColumn(name = "right_task_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTask objectTask;

    public HibernatePreTask() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateTpKey getKey() {
        if (Objects.isNull(leftTaskId) && Objects.isNull(rightTaskId)) {
            return null;
        }
        return new HibernateTpKey(leftTaskId, rightTaskId);
    }

    public void setKey(HibernateTpKey key) {
        if (Objects.isNull(key)) {
            this.leftTaskId = null;
            this.rightTaskId = null;
        } else {
            this.leftTaskId = key.getLeftTaskId();
            this.rightTaskId = key.getRightTaskId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLeftTaskId() {
        return leftTaskId;
    }

    public void setLeftTaskId(Long leftTaskId) {
        this.leftTaskId = leftTaskId;
    }

    public Long getRightTaskId() {
        return rightTaskId;
    }

    public void setRightTaskId(Long rightTaskId) {
        this.rightTaskId = rightTaskId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateTask getSubjectTask() {
        return subjectTask;
    }

    public void setSubjectTask(HibernateTask subjectTask) {
        this.subjectTask = subjectTask;
    }

    public HibernateTask getObjectTask() {
        return objectTask;
    }

    public void setObjectTask(HibernateTask objectTask) {
        this.objectTask = objectTask;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "leftTaskId = " + leftTaskId + ", " +
                "rightTaskId = " + rightTaskId + ", " +
                "remark = " + remark + ", " +
                "subjectTask = " + subjectTask + ", " +
                "objectTask = " + objectTask + ")";
    }
}
