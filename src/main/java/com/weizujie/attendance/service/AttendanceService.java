package com.weizujie.attendance.service;

import com.weizujie.attendance.entity.Attendance;
import com.weizujie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @author weizujie
 */
public interface AttendanceService {
    PageBean<Attendance> queryPage(Map<String, Object> paramMap);

    boolean checkAttendance(Attendance attendance);

    int addAttendance(Attendance attendance);

    int reissue(Integer id);

    List<Attendance> selectList();

    int deleteList(List<Attendance> attendanceList);
}
