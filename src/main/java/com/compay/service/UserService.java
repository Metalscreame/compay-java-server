package com.compay.service;

import com.compay.entity.User;

import java.util.stream.Stream;

public interface UserService {
    User findUserById(Integer id);
    User findUserByEmail(String email);
    User create(User user);
    Stream<User> findAll ();

//    @Query("from User p where p.email = ?")
//    User findByEmail(User user);
}
