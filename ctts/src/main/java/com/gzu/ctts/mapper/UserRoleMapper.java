package com.gzu.ctts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzu.ctts.entity.UserRole;
import com.gzu.ctts.vo.UserRoleVO;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    // 根据用户ID查询用户对应的角色信息
    List<UserRoleVO> getUserRoleVOListByUserIds(List<Long> userIds);
}
