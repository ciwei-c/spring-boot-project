package com.c.model;

import com.c.annotation.Translator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户信息")
public class User {
    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户名称")
    private String userName;
    
    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户账号")
    private String account;

    @Translator("userType")
    @ApiModelProperty("用户类型")
    private String userType;
}
