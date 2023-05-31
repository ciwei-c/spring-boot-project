package com.c.mapper;

import com.c.model.User;

import java.util.List;

public interface UserMapper {

    List<User> getAll();

    List<User> getPageList();

    User getOne(String userId);

    void insert(User user);

    void update(User user);

    void delete(String userId);

}
