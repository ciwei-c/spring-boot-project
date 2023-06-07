package com.c.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSONObject;
import com.c.annotation.Translator;
import com.c.mapper.DictDetailMapper;
import com.c.model.DictDetail;

@RestControllerAdvice(basePackages = "com.c.controller")
public class ResponseResult implements ResponseBodyAdvice<Object> {
  @Autowired
  private DictDetailMapper dictDetailMapper;

  @Override
  @Nullable
  public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {
    BaseResult<?> baseResult;
    if (body instanceof BaseResult) {
      baseResult = (BaseResult<?>) body;
    } else {
      baseResult = BaseResult.successWithData(body);
    }
    Object data = baseResult.getData();
    JSONObject translate =  new JSONObject();
    if(!(data instanceof String) && data != null) {
      if(data instanceof ArrayList) {
        ArrayList<?> _data = (ArrayList<?>) data;
        data = _data.get(0);
      }
      Field[] fields = data.getClass().getDeclaredFields();
      for (Field field : fields) {
        if(field.getAnnotation(Translator.class) != null) {
          String TranslatorValue = field.getAnnotation(Translator.class).value();
          translate.put(TranslatorValue, this.getHandleDictDetails(dictDetailMapper.getAllByCode(TranslatorValue)));
        }
      }
    }
    
    baseResult.setTranslate(translate);
    return baseResult;
  }

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  private JSONObject getHandleDictDetails(List<DictDetail> dictDetails){
    JSONObject handleDictDetail = new JSONObject();
    for(DictDetail dictDetail : dictDetails) {
      handleDictDetail.put(dictDetail.getDictCode(), dictDetail.getName());
    }
    return handleDictDetail;
  }

}
