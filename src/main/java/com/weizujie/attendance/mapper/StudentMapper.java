package com.weizujie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weizujie.attendance.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper extends BaseMapper<Student> {
    List<Student> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int deleteStudent(List<Long> ids);

    int addStudent(Student student);

    Student findById(Long sid);

    int editStudent(Student student);

    Student findByStudent(Student student);

    List<Student> isStudentByClazzId(Long id);

    int editPswdByStudent(Student student);

    int findByName(String name);
}
