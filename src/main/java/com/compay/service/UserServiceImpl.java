package com.compay.service;

import com.compay.entity.User;
import com.compay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    public User findUserById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public User findByEmail(User user) {
//        return null;
//    }
}
