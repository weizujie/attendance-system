package com.weizujie.attendance.service;

import com.weizujie.attendance.entity.Clazz;
import com.weizujie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface ClazzService {

    PageBean<Clazz> queryPage(Map<String, Object> paramMap);

    int deleteClazz(List<Integer> ids);

    int editClazz(Clazz clazz);

    int add(Clazz clazz);
}
