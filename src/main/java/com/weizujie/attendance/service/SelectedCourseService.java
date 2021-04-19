package com.weizujie.attendance.service;

import com.weizujie.attendance.entity.SelectedCourse;
import com.weizujie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface SelectedCourseService {
    PageBean<SelectedCourse> queryPage(Map<String, Object> paramMap);

    int addSelectedCourse(SelectedCourse selectedCourse);

    int deleteSelectedCourse(Long id);

    boolean isStudentId(Long studentId);

    List<SelectedCourse> getAllBySid(Long studentid);
}
