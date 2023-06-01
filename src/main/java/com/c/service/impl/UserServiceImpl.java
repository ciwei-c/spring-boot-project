package com.c.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.c.config.PageRequest;
import com.c.config.PageResult;
import com.c.mapper.UserMapper;
import com.c.service.UserService;
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

  @Override
  public User getOne(String userId) {
    return userMapper.getOne(userId);
  }

  @Override
  public User addUser(User user) {
    String userId = UUID.randomUUID().toString().replace("-", "");
    user.setUserId(userId);
    userMapper.insert(user);
    return userMapper.getOne(userId);
  }

  @Override
  public void deleteUser(String userId) {
    userMapper.delete(userId);
  }

  @Override
  public void updateUser(User user) {
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
