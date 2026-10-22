package com.gzu.ctts.service;

import com.gzu.ctts.entity.Project;

import java.util.List;

public interface ProjectService {

    void addProject(Project project);

    void updateProject(Project project);

    void deleteProject(Long id);

    List<Project> getAllProjects();

    Project getProjectById(Long id);
}
