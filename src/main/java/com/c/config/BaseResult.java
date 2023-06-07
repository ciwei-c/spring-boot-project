package com.c.config;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "响应对象")
public class BaseResult<T> {
    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE =  400;
    private static final String SUCCESS_MESSAGE = "成功";
    @ApiModelProperty(value = "响应码", name = "code", required = true, example = "" + SUCCESS_CODE)
    private int code;
    @ApiModelProperty(value = "响应消息", name = "msg", required = true, example = SUCCESS_MESSAGE)
    private String msg;
    @ApiModelProperty(value = "响应数据", name = "data")
    private T data;
    @ApiModelProperty(value = "字典翻译", name = "translate")
    private JSONObject translate = new JSONObject();
    private BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    private BaseResult() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }
    private BaseResult(int code, String msg) {
        this(code, msg, null);
    }
    private BaseResult(T data) {
        this(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }
    public static <T> BaseResult<T> success() {
        return new BaseResult<>();
    }
    public static <T> BaseResult<T> successWithData(T data) {
        return new BaseResult<>(data);
    }
    public static <T> BaseResult<T> failWithCodeAndMsg(int code, String msg) {
        return new BaseResult<>(code, msg, null);
    }
    public static <T> BaseResult<T> failWithMsg(String msg) {
        return new BaseResult<>(ERROR_CODE, msg, null);
    }
    public static <T> BaseResult<T> buildWithParam(ResponseParam param) {
        return new BaseResult<>(param.getCode(), param.getMsg(), null);
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public void setTranslate(JSONObject translate){
        this.translate = translate;
    }
    public JSONObject getTranslate(){
        return translate;
    }

    public static class ResponseParam {
        private int code;
        private String msg;
        private ResponseParam(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public static ResponseParam buildParam(int code, String msg) {
            return new ResponseParam(code, msg);
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}

