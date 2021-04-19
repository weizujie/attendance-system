package com.weizujie.attendance.service;

import com.weizujie.attendance.entity.Attendance;
import com.weizujie.attendance.utils.PageBean;

import java.util.Map;

public interface AttendanceService {
    PageBean<Attendance> queryPage(Map<String, Object> paramMap);

    boolean isAttendance(Attendance attendance);

    int addAttendance(Attendance attendance);

    int deleteAttendance(Integer id);
}
