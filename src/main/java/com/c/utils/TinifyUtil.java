package com.c.utils;

import java.io.*;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.tinify.Options;
import com.tinify.Source;
import com.tinify.Tinify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TinifyUtil {
  public TinifyUtil() {
    log.info("TinifyUtil:{}", "初始化TinifyUtil");
    Tinify.setKey("Frx02VPQSHFnffN94m6hpGsm81h9rCBD");
  }

  public void compress(String filePath, String fileName) throws IOException {
    Source source = Tinify.fromFile(filePath + fileName);
    source.toFile(filePath + "compress-" + fileName);
  }

  public void resize(String filePath, String fileName, HashMap map) throws IOException {
    Source source = Tinify.fromFile(filePath + fileName);
    Options options = new Options();
    options.with(fileName, options);
    source.toFile(filePath + "compress-" + fileName);
  }
}