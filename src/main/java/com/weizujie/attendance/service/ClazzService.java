package com.weizujie.attendance.service;

import com.weizujie.attendance.entity.Clazz;
import com.weizujie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface ClazzService {

    PageBean<Clazz> queryPage(Map<String, Object> paramMap);

    int addClazz(Clazz clazz);

    int deleteClazz(List<Long> ids);

    int editClazz(Clazz clazz);

    Clazz findByName(String clazzName);

    int add(Clazz clazz);
}
