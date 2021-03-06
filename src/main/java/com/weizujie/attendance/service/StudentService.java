package com.weizujie.attendance.service;

import com.weizujie.attendance.dto.LoginDTO;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PageBean<Student> queryPage(Map<String, Object> paramMap);

    int deleteStudent(List<Integer> ids);

    int addStudent(Student student);

    int editStudent(Student student);

    Student login(LoginDTO loginDTO);

    boolean isStudentByClazzId(Integer next);

    int editPswdByStudent(Student student);

    int updateById(Student student);
}
