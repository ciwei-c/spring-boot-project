package com.c.service;

import java.util.List;

import com.c.config.PageRequest;
import com.c.config.PageResult;
import com.c.model.User;

public interface UserService {
  List<User> getAll();

  User getOne(String userId);

  User addUser(User user);

  void updateUser(User user);

  void deleteUser(String userId);

  PageResult getUserPageList(PageRequest pageRequest);
}
