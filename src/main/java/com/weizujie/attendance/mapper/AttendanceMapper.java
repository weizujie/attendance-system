package com.weizujie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weizujie.attendance.entity.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AttendanceMapper extends BaseMapper<Attendance> {

    List<Attendance> queryList(Map<String, Object> paramMap);

    int queryCount(Map<String, Object> paramMap);

    int reissue(@Param("attendanceId") Integer id);
}
