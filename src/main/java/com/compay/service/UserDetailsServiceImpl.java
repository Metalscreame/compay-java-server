package com.compay.service;

import com.compay.entity.User;
import com.compay.entity.autorization.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        List testList = userService.findByEmail(email);//сетаем то, что мы будем искать
        User user = (User) testList.get(1);//Возвращает первую запись


        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(),
                        roles);
        return userDetails;
    }

}
