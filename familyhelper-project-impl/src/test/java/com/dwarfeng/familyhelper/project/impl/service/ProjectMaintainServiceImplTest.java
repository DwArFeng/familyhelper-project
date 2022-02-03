package com.dwarfeng.familyhelper.project.impl.service;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.familyhelper.project.stack.service.ProjectMaintainService;
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
public class ProjectMaintainServiceImplTest {

    @Autowired
    private ProjectMaintainService projectMaintainService;

    private List<Project> projects;

    @Before
    public void setUp() {
        projects = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Project project = new Project(null, "name", "remark", 12450, new Date(), new Date(), new Date());
            projects.add(project);
        }
    }

    @After
    public void tearDown() {
        projects.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (Project project : projects) {
                project.setKey(projectMaintainService.insert(project));

                Project testProject = projectMaintainService.get(project.getKey());
                assertEquals(BeanUtils.describe(project), BeanUtils.describe(testProject));
                projectMaintainService.update(project);
                testProject = projectMaintainService.get(project.getKey());
                assertEquals(BeanUtils.describe(project), BeanUtils.describe(testProject));
            }
        } finally {
            for (Project project : projects) {
                projectMaintainService.deleteIfExists(project.getKey());
            }
        }
    }
}
