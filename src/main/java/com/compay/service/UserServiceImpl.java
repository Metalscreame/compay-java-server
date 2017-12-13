package com.compay.service;

import com.compay.entity.User;
import com.compay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    public User findUserById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
        return user;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<String> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<String> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
