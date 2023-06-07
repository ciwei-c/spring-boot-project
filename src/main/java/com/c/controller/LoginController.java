package com.c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.c.mapper.UserMapper;
import com.c.service.LoginService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "用户登录")
@RequestMapping("/")
public class LoginController {
  @Autowired
  UserMapper userMapper;

  @Autowired
  LoginService loginService;

  @PostMapping("/login")
  public String login(@RequestBody JSONObject params) {
    return loginService.login(params);
  }

}
