package com.compay.repository;

import com.compay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{


//    @Query("select u from User u)
    List<User> findByName(String name);
    List<User> findByEmail(String name);
    User findById(Integer id);


    @Query("select u from User u where u.name=?1 and u.password=?2")
    User autheinfcate(String name,String password);

    @Query("select u from User u where u.name=?1 and u.email=?2")
    List<User> autheinfcateNameEmail( String name, String email);//TODO убрать это когда в главной странице вход будет через логин и пароль

}

