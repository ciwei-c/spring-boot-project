package com.c.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.c.model.FileModel;

public interface UploadService {
  FileModel upload(MultipartFile file);
  String compress(FileModel fileModel) throws IOException;
  String resize(JSONObject params) throws IOException;
}
