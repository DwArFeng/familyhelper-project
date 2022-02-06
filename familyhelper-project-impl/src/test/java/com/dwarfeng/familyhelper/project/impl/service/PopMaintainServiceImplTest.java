package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Pop;
import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.familyhelper.project.stack.bean.entity.User;
import com.dwarfeng.familyhelper.project.stack.bean.key.PopKey;
import com.dwarfeng.familyhelper.project.stack.service.PopMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.ProjectMaintainService;
import com.dwarfeng.familyhelper.project.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class PopMaintainServiceImplTest {

    private static final long PROJECT_ID = 12450;
    private static final String USER_ID = "test_user";

    @Autowired
    private ProjectMaintainService projectMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private PopMaintainService popMaintainService;

    private Project project;
    private User user;
    private Pop pop;

    @Before
    public void setUp() {
        project = new Project(new LongIdKey(PROJECT_ID), "name", "remark", 12450, new Date(), new Date(), new Date());
        user = new User(new StringIdKey(USER_ID), "remark");
        pop = new Pop(new PopKey(PROJECT_ID, USER_ID), 233, "remark");
    }

    @After
    public void tearDown() {
        project = null;
        user = null;
        pop = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            projectMaintainService.insertOrUpdate(project);
            userMaintainService.insertOrUpdate(user);
            popMaintainService.insert(pop);
            popMaintainService.update(pop);

            Pop testPop = popMaintainService.get(pop.getKey());
            assertEquals(BeanUtils.describe(pop), BeanUtils.describe(testPop));
            testPop = popMaintainService.get(pop.getKey());
            assertEquals(BeanUtils.describe(pop), BeanUtils.describe(testPop));
        } finally {
            projectMaintainService.deleteIfExists(project.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            popMaintainService.deleteIfExists(pop.getKey());
        }
    }

    @Test
    public void testForProjectCascade() throws Exception {
        try {
            projectMaintainService.insertOrUpdate(project);
            userMaintainService.insertOrUpdate(user);
            popMaintainService.insert(pop);

            projectMaintainService.deleteIfExists(project.getKey());
            assertFalse(popMaintainService.exists(pop.getKey()));
        } finally {
            projectMaintainService.deleteIfExists(project.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            popMaintainService.deleteIfExists(pop.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            projectMaintainService.insertOrUpdate(project);
            userMaintainService.insertOrUpdate(user);
            popMaintainService.insert(pop);

            userMaintainService.deleteIfExists(user.getKey());
            assertFalse(popMaintainService.exists(pop.getKey()));
        } finally {
            projectMaintainService.deleteIfExists(project.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            popMaintainService.deleteIfExists(pop.getKey());
        }
    }
}
