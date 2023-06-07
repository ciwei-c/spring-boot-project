package com.c.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.c.exception.GraceException;
import com.c.mapper.UserMapper;
import com.c.model.User;
import com.c.service.LoginService;
import com.c.utils.JwtToken;

@Service
public class LoginServiceImpl implements LoginService {
  @Autowired
  UserMapper userMapper;
  
  @Override
  public String login(JSONObject params){
    String account = params.getString("account");
    String password = params.getString("password");
    if (account == null) {
      GraceException.display("账号不能为空");
    }
    if (password == null) {
      GraceException.display("密码不能为空");
    }
    User user = userMapper.getByAccount(account);

    if(!user.getPassword().equals(password)) {
      GraceException.display("密码不正确，请重新输入密码");
    }

    return JwtToken.getJwtToken(user);
  }
}
