package com.compay.service;

import com.compay.entity.User;

public interface UserService {
    User findUserById(Integer id);
    User create(User user);
}
