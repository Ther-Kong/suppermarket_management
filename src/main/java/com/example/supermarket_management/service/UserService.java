package service;

import org.springframework.stereotype.Service;
import pojo.User;

@Service
public class UserService {
    public User  login(User user) {
        return user;
    }
}
