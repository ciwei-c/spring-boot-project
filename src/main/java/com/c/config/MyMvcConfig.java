package com.c.config;

import com.c.component.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("注册拦截器");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
        // registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
        // //拦截所有请求，包括静态资源文件
        // .excludePathPatterns("/", "/login", "/index.html", "/user/login", "/css/**",
        // "/images/**", "/js/**", "/fonts/**"); //放行登录页，登陆操作，静态资源
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 第一种方式是将 json 处理的转换器放到第一位，使得先让 json 转换器处理返回值，这样 String转换器就处理不了了。
        converters.add(0, new MappingJackson2HttpMessageConverter());
        // 第二种就是把String类型的转换器去掉，不使用String类型的转换器
        // converters.removeIf(httpMessageConverter -> httpMessageConverter.getClass()
        // == StringHttpMessageConverter.class);
    }
}
