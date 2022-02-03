package com.dwarfeng.familyhelper.project.impl.service.operation;

import com.dwarfeng.familyhelper.project.stack.bean.entity.Project;
import com.dwarfeng.familyhelper.project.stack.cache.ProjectCache;
import com.dwarfeng.familyhelper.project.stack.dao.ProjectDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectCrudOperation implements BatchCrudOperation<LongIdKey, Project> {

    private final ProjectDao projectDao;

    private final ProjectCache projectCache;

    @Value("${cache.timeout.entity.project}")
    private long projectTimeout;

    public ProjectCrudOperation(ProjectDao projectDao, ProjectCache projectCache) {
        this.projectDao = projectDao;
        this.projectCache = projectCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return projectCache.exists(key) || projectDao.exists(key);
    }

    @Override
    public Project get(LongIdKey key) throws Exception {
        if (projectCache.exists(key)) {
            return projectCache.get(key);
        } else {
            if (!projectDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Project project = projectDao.get(key);
            projectCache.push(project, projectTimeout);
            return project;
        }
    }

    @Override
    public LongIdKey insert(Project project) throws Exception {
        projectCache.push(project, projectTimeout);
        return projectDao.insert(project);
    }

    @Override
    public void update(Project project) throws Exception {
        projectCache.push(project, projectTimeout);
        projectDao.update(project);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除账本实体自身。
        projectCache.delete(key);
        projectDao.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return projectCache.allExists(keys) || projectDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return projectCache.nonExists(keys) && projectDao.nonExists(keys);
    }

    @Override
    public List<Project> batchGet(List<LongIdKey> keys) throws Exception {
        if (projectCache.allExists(keys)) {
            return projectCache.batchGet(keys);
        } else {
            if (!projectDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Project> projects = projectDao.batchGet(keys);
            projectCache.batchPush(projects, projectTimeout);
            return projects;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Project> projects) throws Exception {
        projectCache.batchPush(projects, projectTimeout);
        return projectDao.batchInsert(projects);
    }

    @Override
    public void batchUpdate(List<Project> projects) throws Exception {
        projectCache.batchPush(projects, projectTimeout);
        projectDao.batchUpdate(projects);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
