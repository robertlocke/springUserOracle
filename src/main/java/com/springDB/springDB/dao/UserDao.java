package com.springDB.springDB.dao;

import com.springDB.springDB.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findById(int id);



}
