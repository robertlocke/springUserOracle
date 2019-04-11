package com.springDB.springDB.controller;

import com.springDB.springDB.entity.ServiceResponse;
import com.springDB.springDB.entity.User;
import com.springDB.springDB.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> addUser(@RequestBody User user){

        this.userService.saveUser(user);
        ServiceResponse<User> response=new ServiceResponse<User>("success", user);
        return new ResponseEntity<Object>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<User>> getUsers(){
        Collection<User> users = this.userService.getAllUsers();

        if(users.isEmpty()){
            return new ResponseEntity<Collection<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> viewUser(@PathVariable int id) {
        User user = this.userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // method = delete, deletes user

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id){
        User user = this.userService.findById(id);
        if(user == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.userService.deleteUser(user);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }

    // method = put, updates user

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody @Valid User user){

        User thisUser =  this.userService.findById(id);
        thisUser.setName(user.getName());
        thisUser.setSalary(user.getSalary());

        this.userService.saveUser(thisUser);
        return new ResponseEntity<User>(thisUser, HttpStatus.NO_CONTENT);

    }

}
