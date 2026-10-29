package com.gzu.ctts.service.impl;

import com.gzu.ctts.entity.Project;
import com.gzu.ctts.mapper.ProjectMapper;
import com.gzu.ctts.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public void addProject(Project project) {
        projectMapper.insert(project);
        System.out.println(project.getId());
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateById(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectMapper.deleteById(id);

    }

    @Override
    public List<Project> getAllProjects() {
        return projectMapper.selectList(null);

    }

    @Override
    public Project getProjectById(Long id) {
        return projectMapper.selectById(id);
    }

    @Override
    public List<Project> getProjectByTitle(String title) {
        return projectMapper.getByTitle(title);
    }
}
