package com.c.controller;

import com.c.config.BaseResult;
import com.c.config.PageRequest;
import com.c.config.PageResult;
import com.c.mapper.UserMapper;
import com.c.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
import java.util.UUID;

@RestController
@Api(tags = "用户管理")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/users")
    @ApiOperation(value = "获取所有用户列表")
    public BaseResult<List<User>> getAllUser(){
        List<User> users = userMapper.getAll();
        return BaseResult.successWithData(users);
    }

    @GetMapping("/user")
    @ApiOperation(value = "获取用户")
    public BaseResult<User> getUser(@RequestParam("userId") String userId){
        User user = userMapper.getOne(userId);
        return BaseResult.successWithData(user);
    }

    @PostMapping("/user/page")
    @ApiOperation(value = "分页获取用户")
    public BaseResult<PageResult> getUserPageList(@RequestBody PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.getPageList();

        PageResult pageResult = PageResult.getPageResult(new PageInfo<User>(users));
        return BaseResult.successWithData(pageResult);
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户")
    public BaseResult<User> addUser(@RequestBody User user){
        String userId = UUID.randomUUID().toString().replace("-", "");
        user.setUserId(userId);
        userMapper.insert(user);
        User addedUser = userMapper.getOne(userId);
        return BaseResult.successWithData(addedUser);
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "删除用户")
    public BaseResult<String> deleteUser(@RequestParam("userId") String userId){
        userMapper.delete(userId);
        return BaseResult.success();
    }

    
    @PutMapping("/user")
    @ApiOperation(value = "更新用户")
    public BaseResult<String> updateUser(@RequestBody User user){
        userMapper.update(user);
        return BaseResult.success();
    }
}
