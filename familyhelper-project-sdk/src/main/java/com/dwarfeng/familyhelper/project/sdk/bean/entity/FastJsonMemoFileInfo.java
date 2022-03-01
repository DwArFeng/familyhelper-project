package com.dwarfeng.familyhelper.project.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoFileInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 备忘录文件信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonMemoFileInfo implements Bean {

    private static final long serialVersionUID = -6296037475710139113L;

    public static FastJsonMemoFileInfo of(MemoFileInfo memoFileInfo) {
        if (Objects.isNull(memoFileInfo)) {
            return null;
        } else {
            return new FastJsonMemoFileInfo(
                    FastJsonLongIdKey.of(memoFileInfo.getKey()),
                    FastJsonLongIdKey.of(memoFileInfo.getMemoKey()),
                    memoFileInfo.getOriginName(), memoFileInfo.getLength(), memoFileInfo.getCreatedDate(),
                    memoFileInfo.getModifiedDate(), memoFileInfo.getInspectedDate(), memoFileInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "memo_key", ordinal = 2)
    private FastJsonLongIdKey memoKey;

    @JSONField(name = "origin_name", ordinal = 3)
    private String originName;

    @JSONField(name = "length", ordinal = 4)
    private long length;

    @JSONField(name = "created_date", ordinal = 5)
    private Date createdDate;

    @JSONField(name = "modified_date", ordinal = 6)
    private Date modifiedDate;

    @JSONField(name = "inspected_date", ordinal = 7)
    private Date inspectedDate;

    @JSONField(name = "remark", ordinal = 8)
    private String remark;

    public FastJsonMemoFileInfo() {
    }

    public FastJsonMemoFileInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey memoKey, String originName, long length, Date createdDate,
            Date modifiedDate, Date inspectedDate, String remark
    ) {
        this.key = key;
        this.memoKey = memoKey;
        this.originName = originName;
        this.length = length;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.inspectedDate = inspectedDate;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getMemoKey() {
        return memoKey;
    }

    public void setMemoKey(FastJsonLongIdKey memoKey) {
        this.memoKey = memoKey;
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

    @Override
    public String toString() {
        return "FastJsonMemoFileInfo{" +
                "key=" + key +
                ", memoKey=" + memoKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", inspectedDate=" + inspectedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
