package com.c.controller;

import com.c.config.PageRequest;
import com.c.config.PageResult;
import com.c.model.User;
import com.c.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

@RestController
@Api(tags = "用户管理")
@RequestMapping("/")
public class UserController {
    @Autowired 
    private UserService userService;

    @GetMapping("/users")
    @ApiOperation(value = "获取所有用户列表")
    public List<User> getAllUser(){
        return userService.getAll();
    }

    @GetMapping("/user")
    @ApiOperation(value = "获取用户")
    public User getUser(@RequestParam("userId") String userId){
        return userService.getOne(userId);
    }

    @PostMapping("/users/page")
    @ApiOperation(value = "分页获取用户")
    public PageResult getUserPageList(@RequestBody PageRequest pageRequest){
        return userService.getUserPageList(pageRequest);
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "删除用户")
    public void deleteUser(@RequestParam("userId") String userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用户")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }
}
