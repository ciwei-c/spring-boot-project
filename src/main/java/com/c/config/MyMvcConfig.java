package com.c.config;

import com.c.component.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class MyMvcConfig implements WebMvcConfigurer {

    @Value("${custom.request.prefix}")
    private String requestPrefix;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("注册拦截器");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(requestPrefix + "/**")
                .excludePathPatterns(requestPrefix + "/login");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 第一种方式是将 json 处理的转换器放到第一位，使得先让 json 转换器处理返回值，这样 String转换器就处理不了了。
        converters.add(0, new MappingJackson2HttpMessageConverter());
        // 第二种就是把String类型的转换器去掉，不使用String类型的转换器
        // converters.removeIf(httpMessageConverter -> httpMessageConverter.getClass()
        // == StringHttpMessageConverter.class);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(requestPrefix, c -> c.isAnnotationPresent(RestController.class));
    }
}
