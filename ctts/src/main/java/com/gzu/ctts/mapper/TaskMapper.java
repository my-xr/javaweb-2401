package com.gzu.ctts.mapper;

import com.gzu.ctts.entity.Task;

public interface TaskMapper {
    Task getById(Long id);

    void update(Task task);
}
