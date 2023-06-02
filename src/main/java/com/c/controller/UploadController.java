package com.c.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.c.model.FileModel;
import com.c.service.UploadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "文件处理")
@RequestMapping("/file")
@RestController
public class UploadController {
  
  @Autowired
  private UploadService uploadService;

  @PostMapping("/upload")
  @ApiOperation(value = "文件上传")
  public Object upload(@RequestParam("file") MultipartFile file) throws IOException {
    return uploadService.upload(file);
  }

  @PostMapping("/compress")
  @ApiOperation(value = "图片压缩")
  public String compress(@RequestBody FileModel fileModel) throws IOException {
    return uploadService.compress(fileModel);
  }

  @PostMapping("/resize")
  @ApiOperation(value = "图片裁剪")
  public String resize(@RequestBody JSONObject params) throws IOException {
    String result = uploadService.resize(params);
    return result;
  }
}
