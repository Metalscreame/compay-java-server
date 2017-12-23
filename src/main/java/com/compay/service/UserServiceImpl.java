package com.compay.service;

import com.compay.entity.User;
import com.compay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User findUserById(Integer id) {
        return userRepository.findOne(id);
    }

//    @Override
//    public User findUserByEmail(String email) {
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
//        return user;
//    }

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    @Transactional
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User autheinfcate(String name,String password) {
        return userRepository.autheinfcate(name,password);
    }

    @Override
    public List<User> autheinfcateNameEmail( String name, String email) {//TODO убрать со временем
        return  userRepository.autheinfcateNameEmail(name,email);
    }

}
