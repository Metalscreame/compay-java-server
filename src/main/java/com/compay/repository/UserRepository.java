package com.compay.repository;

import com.compay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    //@Query("select u from User u")
    List<String> findByName(String name);
    List<String> findByEmail(String name);
}

