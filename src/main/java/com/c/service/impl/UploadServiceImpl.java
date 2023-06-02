package com.c.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.c.exception.GraceException;
import com.c.model.FileModel;
import com.c.service.UploadService;
import com.c.utils.TinifyUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

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

  @Override
  public FileModel upload(MultipartFile file) {
    if (file.isEmpty()) {
      GraceException.display("上传失败，文件不能为空");
    }
    String fileName = "";
    try {
      fileName = this.saveFile(file);
    } catch (IOException e) {
      e.printStackTrace();
      GraceException.display("上传失败");
    }
    FileModel fileModel = new FileModel();
    fileModel.setFileName(fileName);
    return fileModel;
  }

  @Override
  public String compress(FileModel fileModel) throws IOException {
    tinifyUtil.compress(fileModel.getFileName(), fileModel.getFilePath());
    return "https://";
  }

  @Override
  public String resize(JSONObject params) throws IOException {
    JSONObject options = params.getJSONObject("options");
    String fileName = params.getString("fileName");
    String filePath = params.getString("filePath");
    tinifyUtil.resize(fileName, filePath, options);
    return "https://";
  }
}
