package com.c;

import com.tinify.Tinify;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.c.mapper")
public class Application {
    public static void main(String[] args) {
        Tinify.setKey("Frx02VPQSHFnffN94m6hpGsm81h9rCBD");
        SpringApplication.run(Application.class, args);
    }
}
