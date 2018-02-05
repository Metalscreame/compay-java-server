package com.compay.repository;

import com.compay.entity.Adress;
import com.compay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer> {
    List<Adress> findAllByUser(User user);

    @Transactional
    @Modifying
    void deleteById(int id);

    @Query(value = "select a from Adress a where a.user.id= :userId and a.objectDefault=true")
    List<Adress> findDefaultAdressByUsrId(@Param("userId") int userId);
}
