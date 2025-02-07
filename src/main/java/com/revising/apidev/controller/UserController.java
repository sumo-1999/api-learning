package com.revising.apidev.controller;

import com.revising.apidev.entity.User;
import com.revising.apidev.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /*
    API - http://localhost:8080/register
    Body- {
        "userName":"sumit",
        "userEmail":"sumit@gmail.com",
        "userRole":"Admin",
        "password":"sumit"
        }
    * */
    @PostMapping("/register")
    String saveUser(@RequestBody User user){
         return userService.saveUser(user);
    }

    /*
    * http://localhost:8080/login
    * Body - {
        "userName":"sumit",
        "password":"sumit"
        }
    * */
    @PostMapping("/login")
    String getToken(@RequestBody User user){
        return userService.verify(user);
    }
}
