package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.PreTask;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.familyhelper.project.stack.bean.key.TpKey;
import com.dwarfeng.familyhelper.project.stack.service.PreTaskMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.ProjectMaintainService;
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
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class TaskMaintainServiceImplTest {

    @Autowired
    private ProjectMaintainService projectMaintainService;
    @Autowired
    private TaskMaintainService taskMaintainService;
    @Autowired
    private PreTaskMaintainService preTaskMaintainService;

    private Project project;

    private Task taskA;
    private Task taskB;
    private Task taskC;
    private Task taskD;

    private PreTask preTaskBA;
    private PreTask preTaskCA;
    private PreTask preTaskDB;
    private PreTask preTaskDC;

    @Before
    public void setUp() {
        project = new Project(null, "name", "remark", 12450, new Date(), new Date(), new Date());
        taskA = new Task(null, null, "type", "name.a", "remark", 12450, new Date(), new Date(), new Date(), 0, 0);
        taskB = new Task(null, null, "type", "name.b", "remark", 12450, new Date(), new Date(), new Date(), 0, 0);
        taskC = new Task(null, null, "type", "name.c", "remark", 12450, new Date(), new Date(), new Date(), 0, 0);
        taskD = new Task(null, null, "type", "name.d", "remark", 12450, new Date(), new Date(), new Date(), 0, 0);
        preTaskBA = new PreTask(null, "remark");
        preTaskCA = new PreTask(null, "remark");
        preTaskDB = new PreTask(null, "remark");
        preTaskDC = new PreTask(null, "remark");
    }

    @After
    public void tearDown() {
        project = null;
        taskA = null;
        taskB = null;
        taskC = null;
        taskD = null;
        preTaskBA = null;
        preTaskCA = null;
        preTaskDB = null;
        preTaskDC = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            project.setKey(projectMaintainService.insert(project));
            taskA.setProjectKey(project.getKey());
            taskA.setKey(taskMaintainService.insert(taskA));
            taskMaintainService.update(taskA);

            Task testTask = taskMaintainService.get(taskA.getKey());
            assertEquals(BeanUtils.describe(taskA), BeanUtils.describe(testTask));
            testTask = taskMaintainService.get(taskA.getKey());
            assertEquals(BeanUtils.describe(taskA), BeanUtils.describe(testTask));
        } finally {
            taskMaintainService.deleteIfExists(taskA.getKey());
            projectMaintainService.deleteIfExists(project.getKey());
        }
    }

    @Test
    public void testForProjectCascade() throws Exception {
        try {
            project.setKey(projectMaintainService.insert(project));
            taskA.setProjectKey(project.getKey());
            taskA.setKey(taskMaintainService.insert(taskA));

            assertTrue(taskMaintainService.exists(taskA.getKey()));

            projectMaintainService.deleteIfExists(project.getKey());

            assertFalse(taskMaintainService.exists(taskA.getKey()));
        } finally {
            taskMaintainService.deleteIfExists(taskA.getKey());
            projectMaintainService.deleteIfExists(project.getKey());
        }
    }

    @Test
    public void testForPreTaskLookup() throws Exception {
        try {
            taskA.setKey(taskMaintainService.insert(taskA));
            taskB.setKey(taskMaintainService.insert(taskB));
            taskC.setKey(taskMaintainService.insert(taskC));
            taskD.setKey(taskMaintainService.insert(taskD));

            preTaskBA.setKey(new TpKey(taskB.getKey().getLongId(), taskA.getKey().getLongId()));
            preTaskCA.setKey(new TpKey(taskC.getKey().getLongId(), taskA.getKey().getLongId()));
            preTaskDB.setKey(new TpKey(taskD.getKey().getLongId(), taskB.getKey().getLongId()));
            preTaskDC.setKey(new TpKey(taskD.getKey().getLongId(), taskC.getKey().getLongId()));
            preTaskMaintainService.insert(preTaskBA);
            preTaskMaintainService.insert(preTaskCA);
            preTaskMaintainService.insert(preTaskDB);
            preTaskMaintainService.insert(preTaskDC);

            // 任务 A 应该没有任何前置任务。
            List<Task> tasks = taskMaintainService.lookup(
                    TaskMaintainService.PRE_TASK_FOR, new Object[]{taskA.getKey()}
            ).getData();
            assertEquals(0, tasks.size());

            // 任务 B 应该只有前置任务 A。
            tasks = taskMaintainService.lookup(
                    TaskMaintainService.PRE_TASK_FOR, new Object[]{taskB.getKey()}
            ).getData();
            assertEquals(1, tasks.size());
            assertEquals(taskA.getKey(), tasks.get(0).getKey());

            // 任务 C 应该只有前置任务 A。
            tasks = taskMaintainService.lookup(
                    TaskMaintainService.PRE_TASK_FOR, new Object[]{taskC.getKey()}
            ).getData();
            assertEquals(1, tasks.size());
            assertEquals(taskA.getKey(), tasks.get(0).getKey());

            // 任务 D 应该同时有前置任务 B 和 C。
            tasks = taskMaintainService.lookup(
                    TaskMaintainService.PRE_TASK_FOR, new Object[]{taskD.getKey()}
            ).getData();
            assertEquals(2, tasks.size());
            assertTrue(tasks.stream().anyMatch(t -> Objects.equals(taskB.getKey(), t.getKey())));
            assertTrue(tasks.stream().anyMatch(t -> Objects.equals(taskC.getKey(), t.getKey())));
        } finally {
            taskMaintainService.deleteIfExists(taskA.getKey());
            taskMaintainService.deleteIfExists(taskB.getKey());
            taskMaintainService.deleteIfExists(taskC.getKey());
            taskMaintainService.deleteIfExists(taskD.getKey());
            preTaskMaintainService.deleteIfExists(preTaskBA.getKey());
            preTaskMaintainService.deleteIfExists(preTaskCA.getKey());
            preTaskMaintainService.deleteIfExists(preTaskDB.getKey());
            preTaskMaintainService.deleteIfExists(preTaskDC.getKey());
        }
    }
}
