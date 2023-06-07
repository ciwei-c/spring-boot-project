package com.c.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import com.c.model.User;

public class JwtToken {
  // 过期时间： 一天
  public static final long EXPIRE = 1000 * 60 * 60 * 24;
  // 加密密钥
  public static final String APP_SECRET = "abcdefg";

  /**
   * 生成 token 字符串
   * 
   */
  public static String getJwtToken(User user) {
    JwtBuilder jwbBuilder = Jwts.builder();
    String jwtToken = jwbBuilder
        // 设置 token 头部分
        .setHeaderParam("typ", "JWT")
        .setHeaderParam("alg", "HS256")
        // // 设置 token 的主题 subject，自定义
        .setSubject("token")
        // // 设置 token 的创建时间
        .setIssuedAt(new Date())
        // // 设置过期时间，于何时过期
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
        // // 设置 token 的有效载荷
        .claim("userId", user.getUserId())
        .claim("userName", user.getUserName())
        .claim("account", user.getAccount())
        .claim("userType", user.getUserType())
        // // 设置签名，使用的加密算法以及密钥
        .signWith(SignatureAlgorithm.HS256, APP_SECRET)
        .compact();

    return jwtToken;
  }

  /**
   * 判断token是否存在与有效
   * 
   * @param jwtToken
   * @return
   */
  public static boolean checkToken(String jwtToken) {
    jwtToken = jwtToken.replace("Bearer", "");
    if (jwtToken.isEmpty()) {
      return false;
    }
    try {
      Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * 根据token获取userId
   * 
   * @return
   */
  public static String getMemberIdByJwtToken(String jwtToken) {
    jwtToken = jwtToken.replace("Bearer", "");
    if (jwtToken.isEmpty()) {
      return "";
    }
    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
    Claims claims = claimsJws.getBody();
    return (String) claims.get("userId");
  }
}
