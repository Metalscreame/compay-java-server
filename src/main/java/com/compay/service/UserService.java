package com.compay.service;

import com.compay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 19.11.2017.
 */
public interface UserService {
    public User create(User user);
    public User update(User user);
    public User findById(User user);

}
