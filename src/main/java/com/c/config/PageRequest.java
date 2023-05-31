package com.c.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "分页参数")
public class PageRequest {

  @ApiModelProperty(value = "当前页码", name = "pageNum")
  private int pageNum;

  @ApiModelProperty(value = "每页数量", name = "pageSize")
  private int pageSize;
}
