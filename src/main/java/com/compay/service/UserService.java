package com.compay.service;

import com.compay.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserService {
    User findUserById(Integer id);
    User create(User user);

//    @Query("from User p where p.email = ?")
//    User findByEmail(User user);
}
