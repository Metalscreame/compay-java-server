package com.compay.repository;

import com.compay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    List<String> findByName(String name);
    User findByEmail(String name);
    @Query("select e from User e")
    List<User> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password=:p where u.email =:e")
    void updateUser(@Param("p")String password, @Param("e")String email);
    /*
      EXAMPLE:

     * Finds a person by using the last name as a search criteria.
     * @param lastName
     * @return  A list of persons whose last name is an exact match with the given last name.
     *          If no persons is found, this method returns an empty list.

    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    public ObjectDataListList<Person> find(@Param("lastName") String lastName)
     */





}

