package com.weizujie.attendance.service;

import com.weizujie.attendance.dto.LoginDTO;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PageBean<Student> queryPage(Map<String, Object> paramMap);

    int deleteStudent(List<Long> ids);

    int addStudent(Student student);

    Student findById(Long sid);

    int editStudent(Student student);

    Student login(LoginDTO loginDTO);

    boolean isStudentByClazzId(Long next);

    int editPswdByStudent(Student student);

    int findByName(String username);
}
