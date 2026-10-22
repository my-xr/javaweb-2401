package com.gzu.ctts.controller;

import com.gzu.ctts.common.Result;
import com.gzu.ctts.dto.TaskDTO;
import com.gzu.ctts.entity.Task;
import com.gzu.ctts.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getTasks() {
        return taskService.list();
    }

    @PostMapping
    public Result<String> addTask(@RequestBody TaskDTO taskDTO) {

        // 将TaskDTO 复制给 task
        Task task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        taskService.save(task);

        return Result.success("任务添加成功");
    }

    @GetMapping("/{id}")
    public Result<Task> getById(@PathVariable Long id){
        return Result.success(taskService.getById(id));
    }

    @PutMapping("/{id}")
    public Result<String> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {

        // 将TaskDTO 复制给 task
        Task task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        task.setId(id);
        taskService.update(task);

        return Result.success("任务更新成功");
    }

}
