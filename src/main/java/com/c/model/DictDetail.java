package com.c.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("字典值")
public class DictDetail {
    @ApiModelProperty("字典值分类")
    private String code;

    @ApiModelProperty("字典值名称")
    private String name;
    
    @ApiModelProperty("字典值描述")
    private String description;

    @ApiModelProperty("字典值code")
    private String dictCode;
}
