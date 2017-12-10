package com.compay.repository;

import com.compay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    @Query("select u from User u")
    Stream<User> findAllByCustomQueryAndStream();
}

