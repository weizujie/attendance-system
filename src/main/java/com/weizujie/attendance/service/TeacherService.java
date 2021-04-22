package com.weizujie.attendance.service;

import com.weizujie.attendance.dto.LoginDTO;
import com.weizujie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    PageBean<Teacher> queryPage(Map<String, Object> paramMap);

    int deleteTeacher(List<Integer> ids);

    int addTeacher(Teacher teacher);

    int editTeacher(Teacher teacher);

    Teacher login(LoginDTO loginDTO);

    int editPswdByTeacher(Teacher teacher);
}
