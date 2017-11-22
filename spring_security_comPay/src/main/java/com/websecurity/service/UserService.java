package com.websecurity.service;
import com.websecurity.entity.User;

public interface UserService {
    User getUser(String login);
}
