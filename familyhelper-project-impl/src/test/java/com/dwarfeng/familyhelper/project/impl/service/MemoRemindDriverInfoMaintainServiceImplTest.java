package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverInfo;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.MemoRemindDriverInfoMaintainService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class MemoRemindDriverInfoMaintainServiceImplTest {

    @Autowired
    private MemoRemindDriverInfoMaintainService memoRemindDriverInfoMaintainService;
    @Autowired
    private MemoMaintainService memoMaintainService;

    private List<MemoRemindDriverInfo> memoRemindDriverInfos;
    private Memo memo;

    @Before
    public void setUp() {
        memoRemindDriverInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MemoRemindDriverInfo memoRemindDriverInfo = new MemoRemindDriverInfo(
                    null, null, true, "type", "param", "message", "remark"
            );
            memoRemindDriverInfos.add(memoRemindDriverInfo);
        }
        memo = new Memo(
                null, null, "profile", "remark", 12450, new Date(), new Date(), new Date(), true, 12450,
                new Date(), "brief"
        );
    }

    @After
    public void tearDown() {
        memoRemindDriverInfos.clear();
        memo = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (MemoRemindDriverInfo memoRemindDriverInfo : memoRemindDriverInfos) {
                memoRemindDriverInfo.setKey(memoRemindDriverInfoMaintainService.insert(memoRemindDriverInfo));

                MemoRemindDriverInfo testMemoRemindDriverInfo = memoRemindDriverInfoMaintainService.get(memoRemindDriverInfo.getKey());
                assertEquals(BeanUtils.describe(memoRemindDriverInfo), BeanUtils.describe(testMemoRemindDriverInfo));
                memoRemindDriverInfoMaintainService.update(memoRemindDriverInfo);
                testMemoRemindDriverInfo = memoRemindDriverInfoMaintainService.get(memoRemindDriverInfo.getKey());
                assertEquals(BeanUtils.describe(memoRemindDriverInfo), BeanUtils.describe(testMemoRemindDriverInfo));
            }
        } finally {
            for (MemoRemindDriverInfo memoRemindDriverInfo : memoRemindDriverInfos) {
                memoRemindDriverInfoMaintainService.deleteIfExists(memoRemindDriverInfo.getKey());
            }
        }
    }

    @Test
    public void testForMemoCascade() throws Exception {
        try {
            memo.setKey(memoMaintainService.insertOrUpdate(memo));
            for (MemoRemindDriverInfo memoRemindDriverInfo : memoRemindDriverInfos) {
                memoRemindDriverInfo.setMemoKey(memo.getKey());
                memoRemindDriverInfo.setKey(memoRemindDriverInfoMaintainService.insert(memoRemindDriverInfo));
            }

            assertEquals(memoRemindDriverInfos.size(), memoRemindDriverInfoMaintainService.lookup(
                    MemoRemindDriverInfoMaintainService.CHILD_FOR_MEMO, new Object[]{memo.getKey()}
            ).getCount());

            memoMaintainService.deleteIfExists(memo.getKey());

            assertEquals(0, memoRemindDriverInfoMaintainService.lookup(
                    MemoRemindDriverInfoMaintainService.CHILD_FOR_MEMO, new Object[]{memo.getKey()}
            ).getCount());
        } finally {
            memoMaintainService.deleteIfExists(memo.getKey());
            for (MemoRemindDriverInfo memoRemindDriverInfo : memoRemindDriverInfos) {
                memoRemindDriverInfoMaintainService.deleteIfExists(memoRemindDriverInfo.getKey());
            }
        }
    }
}
