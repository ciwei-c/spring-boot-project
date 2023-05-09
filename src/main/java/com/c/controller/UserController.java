package com.c.controller;

import com.c.config.BaseResult;
import com.c.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public BaseResult getUserInfo(){
        User user = new User();
        user.setName("c");
        user.setAge(1);
        return BaseResult.successWithData(user);
    }
}
