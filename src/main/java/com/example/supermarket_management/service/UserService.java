package com.example.supermarket_management.service;

import com.example.supermarket_management.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.supermarket_management.pojo.User;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User login(User user) {
        return userMapper.getUser(user.getUsername(), user.getPwd());
    }

    public int check_user(User user) {
        return userMapper.checkUser(user.getUsername());
    }

    public int register(User user) {
        return userMapper.registerUser(user.getUsername(), user.getPwd());
    }

    public int updatePwd(User user) {
        return userMapper.updatePwdUser(user.getUsername(), user.getPwd());
    }

    public String getUserIdByName(String username) {
        return userMapper.getUserIdByName(username);
    }
}
