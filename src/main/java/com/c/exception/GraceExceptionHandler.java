package com.c.exception;

import com.alibaba.fastjson.JSONObject;
import com.c.config.BaseResult;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常拦截处理
 * 针对异常自定义捕获，返回指定数据结构到前端
 */
@ControllerAdvice
public class GraceExceptionHandler {

    @ExceptionHandler(FileSizeLimitExceededException.class)
    @ResponseBody
    public BaseResult<String> returnFileSizeLimit(FileSizeLimitExceededException e) {
        return BaseResult.failWithMsg("文件大小超出");
    }

    @ExceptionHandler(MyCustomException.class)
    @ResponseBody
    public BaseResult<String> returnMyCustomException(MyCustomException e) {
        JSONObject object = JSONObject.parseObject(e.getMessage());
        int code = (int) object.get("code");
        String message = (String) object.get("message");
        return BaseResult.failWithCodeAndMsg(code, message);
    }

}
