package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Memo;
import com.dwarfeng.familyhelper.project.stack.bean.entity.User;
import com.dwarfeng.familyhelper.project.stack.service.MemoMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
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
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class MemoMaintainServiceImplTest {

    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private MemoMaintainService memoMaintainService;

    private User user;
    private List<Memo> memos;

    @Before
    public void setUp() {
        user = new User(new StringIdKey("test_user"), "remark");
        memos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Memo memo = new Memo(
                    null, null, "profile", "remark", 12450, new Date(), new Date(), new Date(), true, 12450, new Date()
            );
            memos.add(memo);
        }
    }

    @After
    public void tearDown() {
        user = null;
        memos.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            for (Memo memo : memos) {
                memo.setUserKey(user.getKey());
                memo.setKey(memoMaintainService.insert(memo));

                Memo testMemo = memoMaintainService.get(memo.getKey());
                assertEquals(BeanUtils.describe(memo), BeanUtils.describe(testMemo));
                memoMaintainService.update(memo);
                testMemo = memoMaintainService.get(memo.getKey());
                assertEquals(BeanUtils.describe(memo), BeanUtils.describe(testMemo));
            }
        } finally {
            for (Memo memo : memos) {
                memoMaintainService.deleteIfExists(memo.getKey());
            }
            userMaintainService.deleteIfExists(user.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            for (Memo memo : memos) {
                memo.setUserKey(user.getKey());
                memo.setKey(memoMaintainService.insert(memo));
            }

            List<Memo> testMemos = memoMaintainService.lookup(
                    MemoMaintainService.CHILD_FOR_USER, new Object[]{user.getKey()}
            ).getData();
            assertEquals(memos.size(), testMemos.size());
            userMaintainService.deleteIfExists(user.getKey());
            testMemos = memoMaintainService.lookup(
                    MemoMaintainService.CHILD_FOR_USER, new Object[]{user.getKey()}
            ).getData();
            assertTrue(testMemos.isEmpty());
        } finally {
            for (Memo memo : memos) {
                memoMaintainService.deleteIfExists(memo.getKey());
            }
            userMaintainService.deleteIfExists(user.getKey());
        }
    }
}
