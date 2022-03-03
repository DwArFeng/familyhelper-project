package com.dwarfeng.familyhelper.project.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 备忘录文件更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class MemoFileUpdateInfo implements Dto {

    private static final long serialVersionUID = -8173802881940368096L;

    private LongIdKey memoFileKey;
    private String originName;
    private byte[] content;

    public MemoFileUpdateInfo() {
    }

    public MemoFileUpdateInfo(LongIdKey memoFileKey, String originName, byte[] content) {
        this.memoFileKey = memoFileKey;
        this.originName = originName;
        this.content = content;
    }

    public LongIdKey getMemoFileKey() {
        return memoFileKey;
    }

    public void setMemoFileKey(LongIdKey memoFileKey) {
        this.memoFileKey = memoFileKey;
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
        return "MemoFileUpdateInfo{" +
                "memoFileKey=" + memoFileKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
