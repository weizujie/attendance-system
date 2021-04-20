package com.weizujie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weizujie.attendance.entity.Attendance;
import com.weizujie.attendance.mapper.AttendanceMapper;
import com.weizujie.attendance.service.AttendanceService;
import com.weizujie.attendance.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author weizujie
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceMapper attendanceMapper;

    public AttendanceServiceImpl(AttendanceMapper attendanceMapper) {
        this.attendanceMapper = attendanceMapper;
    }

    @Override
    public PageBean<Attendance> queryPage(Map<String, Object> paramMap) {
        PageBean<Attendance> pageBean = new PageBean<>(
                (Integer) paramMap.get("pageno"),
                (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Attendance> attendanceList = attendanceMapper.queryList(paramMap);
        pageBean.setDatas(attendanceList);

        Integer totalSize = attendanceMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalSize);
        return pageBean;
    }

    @Override
    public boolean checkAttendance(Attendance attendance) {
        LambdaQueryWrapper<Attendance> wrapper = new LambdaQueryWrapper<Attendance>()
                .eq(Attendance::getCourseId, attendance.getCourseId())
                .eq(Attendance::getStudentId, attendance.getStudentId())
                .eq(Attendance::getType, attendance.getType());
        return Objects.nonNull(attendanceMapper.selectOne(wrapper));
    }

    @Override
    public int addAttendance(Attendance attendance) {
        return attendanceMapper.insert(attendance);
    }

    @Override
    public int deleteAttendance(Integer id) {
        return attendanceMapper.deleteById(id);
    }
}
