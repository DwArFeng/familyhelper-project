package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.familyhelper.project.stack.service.PreTaskMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.TaskMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class PreTaskMaintainServiceImplTest {

    @Autowired
    private TaskMaintainService taskMaintainService;
    @Autowired
    private PreTaskMaintainService preTaskMaintainService;

    private Task taskA;
    private Task taskB;
    private PreTask preTask;

    @Before
    public void setUp() {
        taskA = new Task(
                null, null, "type", "name.a", "remark", 12450, new Date(), new Date(), new Date(), 0, 0
        );
        taskB = new Task(
                null, null, "type", "name.b", "remark", 12450, new Date(), new Date(), new Date(), 0, 0
        );
        preTask = new PreTask(null, "remark");
    }

    @After
    public void tearDown() {
        taskA = null;
        taskB = null;
        preTask = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            taskA.setKey(taskMaintainService.insert(taskA));
            taskB.setKey(taskMaintainService.insert(taskB));
            preTask.setKey(new TpKey(taskA.getKey().getLongId(), taskB.getKey().getLongId()));
            preTaskMaintainService.insert(preTask);
            preTaskMaintainService.update(preTask);

            PreTask testPreTask = preTaskMaintainService.get(preTask.getKey());
            assertEquals(BeanUtils.describe(preTask), BeanUtils.describe(testPreTask));
            testPreTask = preTaskMaintainService.get(preTask.getKey());
            assertEquals(BeanUtils.describe(preTask), BeanUtils.describe(testPreTask));
        } finally {
            taskMaintainService.deleteIfExists(taskA.getKey());
            taskMaintainService.deleteIfExists(taskB.getKey());
            preTaskMaintainService.deleteIfExists(preTask.getKey());
        }
    }

    @Test
    public void testForSubjectTaskCascade() throws Exception {
        try {
            taskA.setKey(taskMaintainService.insert(taskA));
            taskB.setKey(taskMaintainService.insert(taskB));
            preTask.setKey(new TpKey(taskA.getKey().getLongId(), taskB.getKey().getLongId()));
            preTaskMaintainService.insert(preTask);

            assertTrue(preTaskMaintainService.exists(preTask.getKey()));

            taskMaintainService.deleteIfExists(taskA.getKey());

            assertFalse(preTaskMaintainService.exists(preTask.getKey()));
        } finally {
            taskMaintainService.deleteIfExists(taskA.getKey());
            taskMaintainService.deleteIfExists(taskB.getKey());
            preTaskMaintainService.deleteIfExists(preTask.getKey());
        }
    }

    @Test
    public void testForObjectTaskCascade() throws Exception {
        try {
            taskA.setKey(taskMaintainService.insert(taskA));
            taskB.setKey(taskMaintainService.insert(taskB));
            preTask.setKey(new TpKey(taskA.getKey().getLongId(), taskB.getKey().getLongId()));
            preTaskMaintainService.insert(preTask);

            assertTrue(preTaskMaintainService.exists(preTask.getKey()));

            taskMaintainService.deleteIfExists(taskB.getKey());

            assertFalse(preTaskMaintainService.exists(preTask.getKey()));
        } finally {
            taskMaintainService.deleteIfExists(taskA.getKey());
            taskMaintainService.deleteIfExists(taskB.getKey());
            preTaskMaintainService.deleteIfExists(preTask.getKey());
        }
    }
}
