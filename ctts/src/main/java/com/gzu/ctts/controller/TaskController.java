package com.gzu.ctts.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @PostMapping("/pages")
    public Result<IPage<Task>> getTasks(@RequestBody TaskDTO taskDTO, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        IPage<Task> page = new Page<>(pageNum, pageSize);
        return Result.success(taskService.list(page, taskDTO));
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

    @GetMapping("/getByOptions")
    public Result<List<Task>> getByOptions(@RequestParam(required = false) Long projectId, @RequestParam(required = false) Integer status, @RequestParam(required = false) String title) {
        return Result.success(taskService.getByOptions(projectId, status, title));
    }

    @GetMapping("/getTaskListByStatusList")
    public Result<List<Task>> getTaskListByStatusList(@RequestParam List<Integer> statusList) {
        return Result.success(taskService.getTaskListByStatusList(statusList));
    }

    @PostMapping("/options")
    public Result<List<Task>> getDataByOptions(@RequestBody TaskDTO taskDTO){
        return Result.success(taskService.getDataByOptions(taskDTO));
    }

}
