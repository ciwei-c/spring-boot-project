package com.c.controller;

import com.c.config.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public BaseResult<HashMap<String, Object>> hello(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","helloworld");
        return BaseResult.successWithData(map);
    }
}
