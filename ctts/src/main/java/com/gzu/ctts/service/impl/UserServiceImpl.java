package com.gzu.ctts.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gzu.ctts.dto.UserDTO;
import com.gzu.ctts.entity.Role;
import com.gzu.ctts.entity.User;
import com.gzu.ctts.mapper.RoleMapper;
import com.gzu.ctts.mapper.UserMapper;
import com.gzu.ctts.mapper.UserRoleMapper;
import com.gzu.ctts.service.UserService;
import com.gzu.ctts.vo.UserRoleVO;
import com.gzu.ctts.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRoleMapper userRoleMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleMapper roleMapper;

    @Override
    public UserVO getUserById(Long id) {

        UserVO userVO = new UserVO();
        // 方案一：先查询用户信息，再取数据库中根据用户ID查询用户角色列表（不推荐）
        // 问题：1.步骤多，代码比较复杂 2. 多次查询数据库 不推荐
/*
        // 1. 根据用户ID 查询用户信息
        User user = userMapper.selectById(id);

        BeanUtils.copyProperties(user, userVO);
        // 2. 根据userId，查询角色id
        LambdaQueryWrapper<UserRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserRole::getUserId, id);
        List<UserRole> userRoles = userRoleMapper.selectList(lambdaQueryWrapper);
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).toList();
//        for(UserRole userRole: userRoles){
//            roleIds.add(userRole.getRoleId());
//        }
        // 3. 根据角色ID查询，角色详情
        LambdaQueryWrapper<Role> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper2.in(Role::getId, roleIds);
        List<Role> roles =  roleMapper.selectList(lambdaQueryWrapper2);
        // 4. 把角色的详细信息放到User中
        userVO.setRoles(roles);*/

        // 方案二：用多表连接的方式解决 查询用户同时查询其角色列表（推荐）
        User user = userMapper.selectByIdWithRoles(id);
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }


    // 分页+多表连接  问题：mybatis-plus根据记录数进行分页，导致分页不准确
    @Override
    public List<UserVO> getByPage(IPage<User> page) {
        // 1. 先对主表分页
        List<User> users = userMapper.selectPage(page);
        if (users.isEmpty()) {
            return List.of();
        }

        // 2. 一次性查出这一页用户的角色，并按 userId 分组
        List<Long> userIds = users.stream().map(User::getId).toList();
        Map<Long, List<Role>> roleMap = userRoleMapper.getUserRoleVOListByUserIds(userIds).stream()
                .collect(Collectors.groupingBy(
                        UserRoleVO::getUserId,
                        Collectors.mapping(this::toRole, Collectors.toList())));

        // 3. 组装 VO
        return users.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            userVO.setRoles(roleMap.getOrDefault(user.getId(), List.of()));
            return userVO;
        }).toList();
    }

    @Override
    public UserVO registerUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    private Role toRole(UserRoleVO vo) {
        Role role = new Role();
        role.setId(vo.getRoleId());
        role.setRoleCode(vo.getRoleCode());
        role.setRoleName(vo.getRoleName());
        return role;
    }


}
