package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 备忘录文件上传信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class MemoFileUploadInfo implements Dto {

    private static final long serialVersionUID = 565502273527466219L;

    private LongIdKey memoKey;
    private String originName;
    private byte[] content;

    public MemoFileUploadInfo() {
    }

    public MemoFileUploadInfo(LongIdKey memoKey, String originName, byte[] content) {
        this.memoKey = memoKey;
        this.originName = originName;
        this.content = content;
    }

    public LongIdKey getMemoKey() {
        return memoKey;
    }

    public void setMemoKey(LongIdKey memoKey) {
        this.memoKey = memoKey;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MemoFileUploadInfo{" +
                "memoKey=" + memoKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
