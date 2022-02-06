package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.TaskTypeIndicator;
import com.dwarfeng.familyhelper.project.stack.service.TaskTypeIndicatorMaintainService;
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
public class TaskTypeIndicatorMaintainServiceImplTest {

    @Autowired
    private TaskTypeIndicatorMaintainService taskTypeIndicatorMaintainService;

    private List<TaskTypeIndicator> taskTypeIndicators;

    @Before
    public void setUp() {
        taskTypeIndicators = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TaskTypeIndicator taskTypeIndicator = new TaskTypeIndicator(
                    new StringIdKey("task_type_indicator_test" + i),
                    "label",
                    "remark"
            );
            taskTypeIndicators.add(taskTypeIndicator);
        }
    }

    @After
    public void tearDown() {
        taskTypeIndicators.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (TaskTypeIndicator taskTypeIndicator : taskTypeIndicators) {
                taskTypeIndicator.setKey(taskTypeIndicatorMaintainService.insert(taskTypeIndicator));

                TaskTypeIndicator testTaskTypeIndicator = taskTypeIndicatorMaintainService.get(
                        taskTypeIndicator.getKey());
                assertEquals(BeanUtils.describe(taskTypeIndicator), BeanUtils.describe(testTaskTypeIndicator));
                taskTypeIndicatorMaintainService.update(taskTypeIndicator);
                testTaskTypeIndicator = taskTypeIndicatorMaintainService.get(taskTypeIndicator.getKey());
                assertEquals(BeanUtils.describe(taskTypeIndicator), BeanUtils.describe(testTaskTypeIndicator));
            }
        } finally {
            for (TaskTypeIndicator taskTypeIndicator : taskTypeIndicators) {
                taskTypeIndicatorMaintainService.deleteIfExists(taskTypeIndicator.getKey());
            }
        }
    }
}
