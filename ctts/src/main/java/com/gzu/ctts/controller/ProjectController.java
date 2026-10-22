package com.gzu.ctts.controller;

import com.gzu.ctts.common.Result;
import com.gzu.ctts.dto.ProjectDTO;
import com.gzu.ctts.entity.Project;
import com.gzu.ctts.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Result<String> addProject(@RequestBody ProjectDTO projectDTO) {
        Project project = new Project();
        BeanUtils.copyProperties(projectDTO, project);
        projectService.addProject(project);
        return Result.success("Project added successfully");
    }

    @PutMapping("/{id}")
    public Result<String> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        Project project = new Project();
        BeanUtils.copyProperties(projectDTO, project);
        project.setId(id);
        projectService.updateProject(project);
        return Result.success("Project updated successfully");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return Result.success("Project deleted successfully");
    }

    @GetMapping("/{id}")
    public Result<Project> getProject(@PathVariable Long id) {
        return Result.success(projectService.getProjectById(id));
    }

    @GetMapping
    public Result<List<Project>> getAllProjects() {
        return Result.success(projectService.getAllProjects());
    }


}
