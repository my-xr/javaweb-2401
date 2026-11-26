package com.gzu.ctts.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gzu.ctts.dto.UserDTO;
import com.gzu.ctts.entity.User;
import com.gzu.ctts.vo.UserVO;

import java.util.List;

public interface UserService {

    UserVO getUserById(Long id);

    List<UserVO> getByPage(IPage<User> page);
}
