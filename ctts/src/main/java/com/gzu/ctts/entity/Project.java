package com.gzu.ctts.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@TableName("training_project")
public class Project {
    private Long id;
    private String title;
    private String description;
    private Long teacherId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
