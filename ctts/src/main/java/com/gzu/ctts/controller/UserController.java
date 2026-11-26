package com.gzu.ctts.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzu.ctts.common.Result;
import com.gzu.ctts.entity.User;
import com.gzu.ctts.service.UserService;
import com.gzu.ctts.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public Result<UserVO> getUserById(@PathVariable Long id) {
        return Result.success(userService.getUserById(id));
    }

    @GetMapping
    public Result<List<UserVO>> getAllUsers(@RequestParam int pageNum, @RequestParam int pageSize) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        return Result.success(userService.getByPage(page));
    }

}
