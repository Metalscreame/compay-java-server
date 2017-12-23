package com.compay.service;

import com.compay.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService{
    User findUserById(Integer id);
    //User findUserByEmail(String email);
    User create(User user);
    List<User> findByName(String name);
    List<User> findByEmail(String name);

    User autheinfcate(String name,String password);
    List<User> autheinfcateNameEmail(String name,String email);//TODO убрать со временем

//    @Query("from User p where p.email = ?")
//    User findByEmail(User user);
}
