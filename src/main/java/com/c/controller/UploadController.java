package com.c.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.c.config.BaseResult;
import com.c.model.FileModel;
import com.c.utils.TinifyUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/file")
@RestController
public class UploadController {
  public String filePath;

  @Value("${custom.field.filePath}")
  private void setfilePath(String filePath) {
    this.filePath = filePath;
  }

  TinifyUtil tinifyUtil = new TinifyUtil();

  private String saveFile(MultipartFile file) throws IOException {
    File targetFile = new File(this.filePath);
    if (!targetFile.exists()) {
      targetFile.mkdirs();
    }
    String fileName = file.getOriginalFilename();
    log.info("getOriginalFilename:{}", fileName);
    FileOutputStream out = new FileOutputStream(this.filePath + fileName);
    out.write(file.getBytes());
    out.flush();
    out.close();
    log.info("getpath:{}", targetFile.getPath());
    return fileName;
  }

  @PostMapping("/upload")
  public BaseResult<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {
    if (file.isEmpty()) {
      return BaseResult.failWithMsg("上传失败");
    }
    String fileName = this.saveFile(file);
    FileModel fileModel = new FileModel();
    fileModel.setFileName(fileName);
    return BaseResult.successWithData(fileModel);
  }

  @PostMapping("/compress")
  public BaseResult<String> compress(@RequestBody FileModel fileModel) throws IOException {
    tinifyUtil.compress(fileModel.getFileName(), fileModel.getFilePath());
    return BaseResult.successWithData("https://");
  }

  @PostMapping("/resize")
  public BaseResult<String> resize(@RequestBody JSONObject params) throws IOException {
    JSONObject options = params.getJSONObject("options");
    String fileName = params.getString("fileName");
    String filePath = params.getString("filePath");
    tinifyUtil.resize(fileName, filePath, options);
    return BaseResult.successWithData("https://");
  }
}
