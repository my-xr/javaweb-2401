package com.gzu.ctts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzu.ctts.dao.TaskDao;
import com.gzu.ctts.dto.TaskDTO;
import com.gzu.ctts.entity.Task;
import com.gzu.ctts.mapper.TaskMapper;
import com.gzu.ctts.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public IPage<Task> list(IPage<Task> page, TaskDTO taskDTO) {
        return taskMapper.selectPageVo(page, taskDTO);
    }

    @Override
    public void save(Task task) {
        TaskDao.add(task);
    }

    @Override
    public Task getById(Long id) {
        return taskMapper.getById(id);
    }

    @Override
    public void update(Task task) {
        taskMapper.update(task);
    }

    @Override
    public List<Task> getByOptions(Long projectId, Integer status, String title) {
        return taskMapper.selectByOptions(projectId, status, title);
    }

    @Override
    public List<Task> getTaskListByStatusList(List<Integer> statusList) {
        return taskMapper.selectByStatusList(statusList);
    }

    @Override
    public List<Task> getDataByOptions(TaskDTO dto) {
//        return taskMapper.selectDataByOptions(dto);
        LambdaQueryWrapper<Task> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dto.getProjectId() != null, Task::getProjectId, dto.getProjectId())
                .like(dto.getTitle() != null && !dto.getTitle().isEmpty(), Task::getTitle, dto.getTitle())
                .like(dto.getDescription() != null && !dto.getDescription().isEmpty(), Task::getDescription, dto.getDescription())
                .eq(dto.getStatus() != null, Task::getStatus, dto.getStatus());
        queryWrapper.eq(dto.getDeadline() != null, Task::getDeadline, dto.getDeadline())
                .eq(dto.getTotalScore() != null, Task::getTotalScore, dto.getTotalScore())
                .eq(dto.getCreateTime() != null, Task::getCreateTime, dto.getCreateTime())
                .eq(dto.getUpdateTime() != null, Task::getUpdateTime, dto.getUpdateTime())
                .orderBy(true, false, Task::getCreateTime);
        return taskMapper.selectList(queryWrapper);
    }
}
