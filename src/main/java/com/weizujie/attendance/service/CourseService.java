package com.weizujie.attendance.service;

import com.weizujie.attendance.entity.Course;
import com.weizujie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface CourseService {
    PageBean<Course> queryPage(Map<String, Object> paramMap);

    int addCourse(Course course);

    int editCourse(Course course);

    int deleteCourse(List<Long> ids);

    List<Course> getCourseById(List<Long> ids);
}
