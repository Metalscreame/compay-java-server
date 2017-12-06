package com.compay.service;

import com.compay.entity.User;

public interface UserService {

    User findUserById(Integer id);

    User create(User user);

//    @Query("from User p where p.email = ?")
//    User findByEmail(User user);
}
