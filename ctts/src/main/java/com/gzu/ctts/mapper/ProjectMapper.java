package com.gzu.ctts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzu.ctts.entity.Project;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProjectMapper extends BaseMapper<Project> {

    List<Project> getByTitle(String title);
}
