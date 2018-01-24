package com.compay.service;

import com.compay.entity.User;
import com.compay.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
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
        //sha-1 encoding before saving to the database
        byte[] bytes = new byte[0];
        try {
            bytes = user.getPassword().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encoded = Base64.getEncoder().encodeToString(bytes);
        user.setPassword(encoded);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public List<String> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(String password, String email) {
        byte[] bytes = new byte[0];
        try {
            bytes = password.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encoded = Base64.getEncoder().encodeToString(bytes);
        userRepository.updateUser(encoded,email);
    }
}
