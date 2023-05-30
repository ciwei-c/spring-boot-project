package com.c.controller;

import com.c.config.BaseResult;
import com.c.mapper.UserMapper;
import com.c.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/user")
    public BaseResult<User> getUser(@RequestParam("userId") String userId){
        User user = userMapper.getOne(userId);
        return BaseResult.successWithData(user);
    }

    @PostMapping("/user")
    public BaseResult<User> addUser(@RequestBody User user){
        String userId = UUID.randomUUID().toString().replace("-", "");
        user.setUserId(userId);
        userMapper.insert(user);
        User addedUser = userMapper.getOne(userId);
        return BaseResult.successWithData(addedUser);
    }

    @DeleteMapping("/user")
    public BaseResult<String> deleteUser(@RequestParam("userId") String userId){
        userMapper.delete(userId);
        return BaseResult.success();
    }

    
    @PutMapping("/user")
    public BaseResult<String> updateUser(@RequestBody User user){
        userMapper.update(user);
        return BaseResult.success();
    }
}
