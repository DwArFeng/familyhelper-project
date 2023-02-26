package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.MemoRemindDriverSupport;
import com.dwarfeng.familyhelper.project.stack.service.MemoRemindDriverSupportMaintainService;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class MemoRemindDriverSupportMaintainServiceImplTest {

    @Autowired
    private MemoRemindDriverSupportMaintainService service;

    private final List<MemoRemindDriverSupport> memoRemindDriverSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            MemoRemindDriverSupport memoRemindDriverSupport = new MemoRemindDriverSupport(
                    new StringIdKey("memoRemindDriver-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            memoRemindDriverSupports.add(memoRemindDriverSupport);
        }
    }

    @After
    public void tearDown() {
        memoRemindDriverSupports.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (MemoRemindDriverSupport memoRemindDriverSupport : memoRemindDriverSupports) {
                memoRemindDriverSupport.setKey(service.insert(memoRemindDriverSupport));
                service.update(memoRemindDriverSupport);
                MemoRemindDriverSupport testMemoRemindDriverSupport = service.get(memoRemindDriverSupport.getKey());
                assertEquals(BeanUtils.describe(memoRemindDriverSupport), BeanUtils.describe(testMemoRemindDriverSupport));
            }
        } finally {
            for (MemoRemindDriverSupport memoRemindDriverSupport : memoRemindDriverSupports) {
                service.delete(memoRemindDriverSupport.getKey());
            }
        }
    }
}
