package com.c.exception;

/**
 * 处理异常，进行调用
 */
public class GraceException {
    public static void display(String message) {
        throw new MyCustomException(message);
    }
}
