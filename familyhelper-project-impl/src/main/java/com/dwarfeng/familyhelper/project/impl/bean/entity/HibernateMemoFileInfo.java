package com.dwarfeng.familyhelper.project.impl.bean.entity;

import com.dwarfeng.familyhelper.project.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_memo_file_info", indexes = {
        @Index(name = "idx_created_date", columnList = "memo_id, created_date"),
        @Index(name = "idx_modified_date", columnList = "memo_id, modified_date"),
        @Index(name = "idx_inspected_date", columnList = "memo_id, inspected_date")
})
public class HibernateMemoFileInfo implements Bean {

    private static final long serialVersionUID = -3264644731816142027L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "memo_id")
    private Long memoLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "origin_name")
    private String originName;

    @Column(name = "column_length")
    private long length;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "inspected_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inspectedDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateMemo.class)
    @JoinColumns({ //
            @JoinColumn(name = "memo_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateMemo memo;

    public HibernateMemoFileInfo() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getMemoKey() {
        return Optional.ofNullable(memoLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setMemoKey(HibernateLongIdKey idKey) {
        this.memoLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getMemoLongId() {
        return memoLongId;
    }

    public void setMemoLongId(Long memoLongId) {
        this.memoLongId = memoLongId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
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

    public Date getInspectedDate() {
        return inspectedDate;
    }

    public void setInspectedDate(Date inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateMemo getMemo() {
        return memo;
    }

    public void setMemo(HibernateMemo memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "memoLongId = " + memoLongId + ", " +
                "originName = " + originName + ", " +
                "length = " + length + ", " +
                "createdDate = " + createdDate + ", " +
                "modifiedDate = " + modifiedDate + ", " +
                "inspectedDate = " + inspectedDate + ", " +
                "remark = " + remark + ", " +
                "memo = " + memo + ")";
    }
}
