package com.manage.sys.controller;

import com.manage.VO.Result;
import com.manage.sys.entity.User;
import com.manage.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangBai
 * @since 2023-08-03
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public Result<List<User>> getAllUser() {
        return Result.success("查询成功", userService.list());
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> data = userService.login(user);
        if (data != null) return Result.success(data);
        return Result.Failed(200002, "用户名或密码错误");
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam("token") String token) {
        // 根据token获取用户信息 redis
        Map<String, Object> data = userService.getUserInfo(token);
        if (data == null) return Result.Failed(20003, "登录失效，重新登录");
        return Result.success(data);
    }
}
