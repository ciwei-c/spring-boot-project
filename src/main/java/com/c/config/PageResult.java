package com.c.config;

import java.util.List;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "分页返回数据结构")
public class PageResult {

  @ApiModelProperty(value = "当前页码", name = "pageNum")
  private int pageNum;

  @ApiModelProperty(value = "每页数量", name = "pageSize")
  private int pageSize;

  @ApiModelProperty(value = "总数量", name = "total")
  private long total;

  @ApiModelProperty(value = "总页数", name = "totalPages")
  private int totalPages;

  @ApiModelProperty(value = "列表内容", name = "list")
  private List<?> list;

  private PageResult(int pageNum, int pageSize, long total, int totalPages, List<?> list) {
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.total = total;
    this.totalPages = totalPages;
    this.list = list;
  }

  public static PageResult getPageResult(PageInfo<?> pageInfo){
    return new PageResult(pageInfo.getPageNum(),pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
  }
}
