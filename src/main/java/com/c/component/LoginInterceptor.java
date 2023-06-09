package com.c.component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.c.exception.GraceException;
import com.c.utils.JwtToken;
import com.c.utils.UserRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("开始预处理【在业务处理器处理请求之前被调用】");
        String token = UserRequest.getToken();
        log.info("获取token:{}", token);
        log.info("请求路径:{}", request.getRequestURL());
        if (token == null || !JwtToken.checkToken(token)) {
            GraceException.display("认证失败", 401);
        }
        return true;
    }

    /**
     * 目标方法执行后
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        log.info("postHandle执行{}", modelAndView);
    }

    /**
     * 页面渲染后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        log.info("afterCompletion执行异常{}", ex);
    }
}
