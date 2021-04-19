package com.weizujie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weizujie.attendance.entity.SelectedCourse;

import java.util.List;
import java.util.Map;

public interface SelectedCourseMapper extends BaseMapper<SelectedCourse> {
    List<SelectedCourse> queryList(Map<String, Object> paramMap);

    int queryCount(Map<String, Object> paramMap);

    int addSelectedCourse(SelectedCourse selectedCourse);

    SelectedCourse findBySelectedCourse(SelectedCourse selectedCourse);

    SelectedCourse findById(Integer id);

    int deleteSelectedCourse(Integer id);

    List<SelectedCourse> isStudentId(Integer id);

    List<SelectedCourse> getAllBySid(Integer studentid);
}
