package com.gzu.ctts.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Task {
    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Integer totalScore;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
