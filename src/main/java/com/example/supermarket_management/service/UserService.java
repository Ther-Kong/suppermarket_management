package com.example.supermarket_management.service;

import org.springframework.stereotype.Service;
import com.example.supermarket_management.pojo.User;

@Service
public class UserService {
    public User  login(User user) {
        return user;
    }
}
