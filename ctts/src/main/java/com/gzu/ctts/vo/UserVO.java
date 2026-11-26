package com.gzu.ctts.vo;

import com.gzu.ctts.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserVO {

    private Long id;
    private String username;
//    private String password;
    private String realName;
    private String phone;
    private String email;
    private Integer status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private List<Role> roles;
}
