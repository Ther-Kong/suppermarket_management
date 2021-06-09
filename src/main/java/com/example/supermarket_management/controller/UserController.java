package com.example.supermarket_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.supermarket_management.pojo.User;
import com.example.supermarket_management.util.JwtUtil;
import com.example.supermarket_management.util.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) throws Exception {
        System.out.println("111");
        //初始化返回值
        Result<String> result = new Result<>();

        //用户登录校验
        User loginUser = userService.login(user);

        //没有抛出异常表示正常
        result.setCode(HttpStatus.OK.value());
        result.setMsg("认证成功！");

        //声明payload
        Map<String, String> payload = new HashMap<>(2);

        //初始化payload
        payload.put("id", loginUser.getId().toString());
        payload.put("username", loginUser.getUsername());

        //获取令牌
        String token = JwtUtil.getToken(payload, 20);

        //在响应结果中添加token
        result.setData(token);

        //返回结果
        return result;
    }

    @GetMapping("/list")
    public Result<List<User>> userList() throws Exception {

        //初始化返回值
        Result<List<User>> result = new Result<>();
        //如果成功，设置状态码和查询到的结果
        result.setCode(HttpStatus.OK.value());
        result.setMsg("查询成功！");
//        List<User> users = userService.userList();
//        result.setData(users);
        //返回结果
        return result;
    }
}
