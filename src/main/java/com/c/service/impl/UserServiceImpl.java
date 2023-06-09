package com.c.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.c.config.PageRequest;
import com.c.config.PageResult;
import com.c.exception.GraceException;
import com.c.mapper.UserMapper;
import com.c.service.UserService;
import com.c.utils.JwtToken;
import com.c.utils.UserRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.c.model.User;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserMapper userMapper;

  @Override
  public List<User> getAll() {
    List<User> users = userMapper.getAll();
    return users;
  }

  public User checkUserExist(String userId) {
    User user = userMapper.getOne(userId);
    if (user == null) {
      GraceException.display("未找到用户");
    }
    return user;
  }

  public void checkTokenIsCurrentUser(String userId) {
    String token = UserRequest.getToken();
    String userIdFromToken = JwtToken.getUserIdByJwtToken(token);
    if (!userIdFromToken.equals(userId)) {
      GraceException.display("当前token无法操作此用户");
    }
  }

  @Override
  public User getOne(String userId) {
    User user = userMapper.getOne(userId);
    this.checkUserExist(userId);
    return user;
  }

  @Override
  public User addUser(User user) {
    String userId = UUID.randomUUID().toString().replace("-", "");

    String userName = user.getUserName();
    String account = user.getAccount();
    String password = user.getPassword();

    if (userName == null) {
      GraceException.display("用户名不能为空");
    }
    if (account == null) {
      GraceException.display("账号不能为空");
    }
    if (password == null) {
      GraceException.display("密码不能为空");
    }
    if (userMapper.getByUserName(userName) != null) {
      GraceException.display("用户名已存在");
    }
    ;
    if (userMapper.getByAccount(account) != null) {
      GraceException.display("账号已被注册");
    }
    ;
    user.setUserId(userId);
    userMapper.insert(user);
    return userMapper.getOne(userId);
  }

  @Override
  public void deleteUser(String userId) {
    this.checkUserExist(userId);
    this.checkTokenIsCurrentUser(userId);
    userMapper.delete(userId);
  }

  @Override
  public void updateUser(User user) {
    String userId = user.getUserId();
    User userByUserId = this.checkUserExist(userId);
    String userName = user.getUserName();
    if (userName != null) {
      User userByUserName = userMapper.getByUserName(userName);
      if (userByUserName != null && !userByUserId.getUserName().equals(userName)) {
        GraceException.display("用户名已存在");
      }
    }
    this.checkTokenIsCurrentUser(userId);
    userMapper.update(user);
  }

  @Override
  public PageResult getUserPageList(PageRequest pageRequest) {
    int pageNum = pageRequest.getPageNum();
    int pageSize = pageRequest.getPageSize();

    PageHelper.startPage(pageNum, pageSize);
    List<User> users = userMapper.getPageList();

    PageResult pageResult = PageResult.getPageResult(new PageInfo<User>(users));
    return pageResult;
  }
}
