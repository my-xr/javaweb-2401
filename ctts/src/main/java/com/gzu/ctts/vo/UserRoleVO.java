package com.gzu.ctts.vo;

import lombok.Data;

@Data
public class UserRoleVO {

    private Long userId;
    private Long roleId;
    private String roleName;
    private String roleCode;

}
