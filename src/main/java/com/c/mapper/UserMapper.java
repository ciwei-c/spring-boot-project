package com.c.mapper;

import com.c.model.User;

import java.util.List;

public interface UserMapper {

    List<User> getAll();

    List<User> getPageList();

    User getOne(String userId);

    User getByUserName(String userName);

    User getByAccount(String Account);

    void insert(User user);

    void update(User user);

    void delete(String userId);

}
