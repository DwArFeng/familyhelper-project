package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Task;
import com.dwarfeng.familyhelper.project.stack.bean.entity.TimePoint;
import com.dwarfeng.familyhelper.project.stack.service.TaskMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.TimePointMaintainService;
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
public class TimePointMaintainServiceImplTest {

    @Autowired
    private TaskMaintainService taskMaintainService;
    @Autowired
    private TimePointMaintainService timePointMaintainService;

    private Task task;
    private List<TimePoint> timePoints;

    @Before
    public void setUp() {
        task = new Task(null, null, "type", "name.a", "remark", 12450, new Date(), new Date(), new Date(), 0, 0);
        timePoints = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TimePoint timePoint = new TimePoint(null, null, "profile", "remark", 12450, new Date(), new Date());
            timePoints.add(timePoint);
        }
    }

    @After
    public void tearDown() {
        task = null;
        timePoints.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            task.setKey(taskMaintainService.insert(task));
            for (TimePoint timePoint : timePoints) {
                timePoint.setTaskKey(task.getKey());
                timePoint.setKey(timePointMaintainService.insert(timePoint));

                TimePoint testTimePoint = timePointMaintainService.get(timePoint.getKey());
                assertEquals(BeanUtils.describe(timePoint), BeanUtils.describe(testTimePoint));
                timePointMaintainService.update(timePoint);
                testTimePoint = timePointMaintainService.get(timePoint.getKey());
                assertEquals(BeanUtils.describe(timePoint), BeanUtils.describe(testTimePoint));
            }
        } finally {
            for (TimePoint timePoint : timePoints) {
                timePointMaintainService.deleteIfExists(timePoint.getKey());
            }
            taskMaintainService.deleteIfExists(task.getKey());
        }
    }

    @Test
    public void testForTaskCascade() throws Exception {
        try {
            task.setKey(taskMaintainService.insert(task));
            for (TimePoint timePoint : timePoints) {
                timePoint.setTaskKey(task.getKey());
                timePoint.setKey(timePointMaintainService.insert(timePoint));
            }

            List<TimePoint> testTimePoints = timePointMaintainService.lookup(
                    TimePointMaintainService.CHILD_FOR_TASK, new Object[]{task.getKey()}
            ).getData();
            assertEquals(timePoints.size(), testTimePoints.size());
            taskMaintainService.deleteIfExists(task.getKey());
            testTimePoints = timePointMaintainService.lookup(
                    TimePointMaintainService.CHILD_FOR_TASK, new Object[]{task.getKey()}
            ).getData();
            assertTrue(testTimePoints.isEmpty());
        } finally {
            for (TimePoint timePoint : timePoints) {
                timePointMaintainService.deleteIfExists(timePoint.getKey());
            }
            taskMaintainService.deleteIfExists(task.getKey());
        }
    }
}
