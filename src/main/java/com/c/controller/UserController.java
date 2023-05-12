package com.c.controller;

import com.c.config.BaseResult;
import com.c.mapper.UserMapper;
import com.c.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/users")
    public BaseResult<List<User>> getAllUser(){
        List<User> users = userMapper.getAll();
        return BaseResult.successWithData(users);
    }
}
