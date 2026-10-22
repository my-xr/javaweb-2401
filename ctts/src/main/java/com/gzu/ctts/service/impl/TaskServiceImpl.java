package com.gzu.ctts.service.impl;

import com.gzu.ctts.dao.TaskDao;
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
    public List<Task> list() {
        return TaskDao.list();
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
}
