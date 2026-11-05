package com.gzu.ctts.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gzu.ctts.dto.TaskDTO;
import com.gzu.ctts.entity.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskService {

    IPage<Task> list(IPage<Task> page, TaskDTO taskDTO);

    void save(Task task);

    Task getById(Long id);

    void update(Task task);

    List<Task> getByOptions(Long projectId, Integer status, String title);

    List<Task> getTaskListByStatusList(List<Integer> statusList);

    List<Task> getDataByOptions(TaskDTO dto);

}
