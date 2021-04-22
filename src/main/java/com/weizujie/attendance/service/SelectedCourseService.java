package com.weizujie.attendance.service;

import com.weizujie.attendance.entity.Course;
import com.weizujie.attendance.entity.SelectedCourse;
import com.weizujie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface SelectedCourseService {
    PageBean<SelectedCourse> queryPage(Map<String, Object> paramMap);

    int addSelectedCourse(SelectedCourse selectedCourse);

    int deleteSelectedCourse(Integer id);

    boolean checkSelectedCourse(Integer studentId);

    List<SelectedCourse> getAllBySid(Integer studentId);

    Course getCourseDetail(Integer studentId, Integer courseId);
}
