package com.c.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class UserRequest {
  private static String tokenName;

  @Value("${custom.token.name}")
  private void setTokenName(String name) {
    UserRequest.tokenName = name;
  }

  public static String getToken() {
    String token = null;
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    if (servletRequestAttributes != null) {
      HttpServletRequest request = servletRequestAttributes.getRequest();
      token = request.getHeader(UserRequest.tokenName);
    }
    return token;
  }
}
