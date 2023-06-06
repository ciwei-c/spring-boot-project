package com.c.mapper;

import java.util.List;

import com.c.model.DictDetail;

public interface DictDetailMapper {

    List<DictDetail> getAllByCode(String code);

}
