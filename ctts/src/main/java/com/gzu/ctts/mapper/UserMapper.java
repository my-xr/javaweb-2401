package com.gzu.ctts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gzu.ctts.dto.UserDTO;
import com.gzu.ctts.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    User selectById(Long id);

    User selectByIdWithRoles(Long id);

    List<User> selectPageWithRoles(IPage<User> page);

    List<User> selectPage(IPage<User> page);

}
