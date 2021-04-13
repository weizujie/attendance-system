package com.weizujie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weizujie.attendance.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseMapper extends BaseMapper<Course> {
    List<Course> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int addCourse(Course course);

    int editCourse(Course course);

    int deleteCourse(List<Long> ids);

    int addStudentNum(Long courseId);

    void deleteStudentNum(Long courseId);

    List<Course> getCourseById(List<Long> ids);

    long findByName(String name);
}
