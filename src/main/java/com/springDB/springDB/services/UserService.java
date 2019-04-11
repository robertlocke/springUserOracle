package com.springDB.springDB.services;

import com.springDB.springDB.dao.UserDao;
import com.springDB.springDB.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> getAllUsers() {
        return this.userDao.findAll();
    }

    public User findById(int id) {
        return this.userDao.findById(id);
    }


    public User saveUser(User user) {
        return this.userDao.save(user);
    }

    public User deleteUser(User user) {
        this.userDao.delete(user);
        return new User();

    }

    public User updateUser(User user, int id) {
        return this.userDao.save(user);
    }

    

    //other methods omitted for brevity
}
