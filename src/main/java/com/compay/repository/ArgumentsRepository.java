package com.compay.repository;

import com.compay.entity.Arguments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArgumentsRepository extends JpaRepository<Arguments, Integer> {
    Arguments findByName(String name);
}
