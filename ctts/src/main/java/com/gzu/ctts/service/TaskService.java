package com.gzu.ctts.service;

import com.gzu.ctts.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> list();

    void save(Task task);

    Task getById(Long id);

    void update(Task task);

}
