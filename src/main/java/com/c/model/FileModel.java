package com.c.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("文件信息")
public class FileModel {
  @ApiModelProperty("文件id")
  private String fileId;

  @ApiModelProperty("文件名称")
  private String fileName;
  
  @ApiModelProperty("文件路径")
  private String filePath;
}
