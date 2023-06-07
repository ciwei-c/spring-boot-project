package com.c.utils;

import java.io.*;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tinify.Options;
import com.tinify.Source;
import com.tinify.Tinify;

@Component
public class TinifyUtil {

  public TinifyUtil() {
    Tinify.setKey("Frx02VPQSHFnffN94m6hpGsm81h9rCBD");
  }

  public void compress(String fileName, String filePath) throws IOException {
    Source source = Tinify.fromFile(filePath + fileName);
    source.toFile(filePath + "compress-" + fileName);
  }

  public void resize(String fileName, String filePath, JSONObject resizeOptions) throws IOException {
    Source source = Tinify.fromFile(filePath + fileName);
    Options options = new Options();
    for (Map.Entry<String, Object> entry : resizeOptions.entrySet()) {
      options.with(entry.getKey(), entry.getValue());
    }
    Source resizedFile = source.resize(options);
    resizedFile.toFile(filePath + "resize-" + fileName);
  }
}