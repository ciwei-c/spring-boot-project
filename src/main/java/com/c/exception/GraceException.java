package com.c.exception;

import com.alibaba.fastjson.JSONObject;


class MessageHandle {
    public static String handel(String message, int code){
        JSONObject object = new JSONObject();
        object.put("message", message);
        object.put("code", code);
        return object.toJSONString();
    }
}

/**
 * 处理异常，进行调用
 */
public class GraceException {
    public static void display(String message) {
        throw new MyCustomException(MessageHandle.handel(message, 400));
    }
    public static void display(String message, int code) {
        throw new MyCustomException(MessageHandle.handel(message, code));
    }
}
