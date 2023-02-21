package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoFileInfo;
import com.dwarfeng.familyhelper.project.stack.service.MemoFileInfoMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class MemoFileInfoMaintainServiceImplTest {

    @Autowired
    private MemoFileInfoMaintainService memoFileInfoMaintainService;
    @Autowired
    private MemoMaintainService memoMaintainService;

    private List<MemoFileInfo> memoFileInfos;
    private Memo memo;

    @Before
    public void setUp() {
        memoFileInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MemoFileInfo memoFileInfo = new MemoFileInfo(
                    null, null, "originName", 12450, new Date(), new Date(), new Date(), "remark"
            );
            memoFileInfos.add(memoFileInfo);
        }
        memo = new Memo(
                null, null, "profile", "remark", 12450, new Date(), new Date(), new Date(), true, 12450, new Date()
        );
    }

    @After
    public void tearDown() {
        memoFileInfos.clear();
        memo = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            memo.setKey(memoMaintainService.insert(memo));
            for (MemoFileInfo memoFileInfo : memoFileInfos) {
                memoFileInfo.setMemoKey(memo.getKey());
                memoFileInfo.setKey(memoFileInfoMaintainService.insert(memoFileInfo));

                MemoFileInfo testMemoFileInfo = memoFileInfoMaintainService.get(memoFileInfo.getKey());
                assertEquals(BeanUtils.describe(memoFileInfo), BeanUtils.describe(testMemoFileInfo));
                memoFileInfoMaintainService.update(memoFileInfo);
                testMemoFileInfo = memoFileInfoMaintainService.get(memoFileInfo.getKey());
                assertEquals(BeanUtils.describe(memoFileInfo), BeanUtils.describe(testMemoFileInfo));
            }
        } finally {
            for (MemoFileInfo memoFileInfo : memoFileInfos) {
                memoFileInfoMaintainService.deleteIfExists(memoFileInfo.getKey());
            }
            memoMaintainService.deleteIfExists(memo.getKey());
        }
    }

    @Test
    public void testForMemoCascade() throws Exception {
        try {
            memo.setKey(memoMaintainService.insert(memo));
            for (MemoFileInfo memoFileInfo : memoFileInfos) {
                memoFileInfo.setMemoKey(memo.getKey());
                memoFileInfo.setKey(memoFileInfoMaintainService.insert(memoFileInfo));
            }

            memoMaintainService.deleteIfExists(memo.getKey());

            assertTrue(memoFileInfoMaintainService.nonExists(
                    memoFileInfos.stream().map(MemoFileInfo::getKey).collect(Collectors.toList()))
            );
        } finally {
            for (MemoFileInfo memoFileInfo : memoFileInfos) {
                memoFileInfoMaintainService.deleteIfExists(memoFileInfo.getKey());
            }
            memoMaintainService.deleteIfExists(memo.getKey());
        }
    }
}
