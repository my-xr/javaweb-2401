package com.gzu.ctts.mapper;

import com.gzu.ctts.entity.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {
    Task getById(Long id);

    void update(Task task);

    List<Task> selectByOptions(@Param("projectId") Long projectId,@Param("projectId")  Integer status,@Param("projectId")  String title);

    List<Task> selectByStatusList(@Param("statusList") List<Integer> statusList);
}
