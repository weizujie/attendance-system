package com.weizujie.attendance.controller;

import com.weizujie.attendance.constants.Constant;
import com.weizujie.attendance.entity.Attendance;
import com.weizujie.attendance.entity.SelectedCourse;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.service.AttendanceService;
import com.weizujie.attendance.service.CourseService;
import com.weizujie.attendance.service.SelectedCourseService;
import com.weizujie.attendance.utils.DateFormatUtil;
import com.weizujie.attendance.utils.PageBean;
import com.weizujie.attendance.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private SelectedCourseService selectedCourseService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/attendance_list")
    public String attendanceList() {
        return "/attendance/attendanceList";
    }

    /**
     * 异步获取考勤列表数据
     */
    @RequestMapping("/getAttendanceList")
    @ResponseBody
    public Object getAttendanceList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                    @RequestParam(value = "studentid", defaultValue = "0") String studentid,
                                    @RequestParam(value = "courseid", defaultValue = "0") String courseid,
                                    String type, String date, String from, HttpSession session) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!studentid.equals("0")) {
            paramMap.put("studentid", studentid);
        }
        if (!courseid.equals("0")) {
            paramMap.put("courseid", courseid);
        }
        if (!StringUtils.isEmpty(type)) {
            paramMap.put("type", type);
        }
        if (!StringUtils.isEmpty(date)) {
            paramMap.put("date", date);
        }

        Student student = (Student) session.getAttribute(Constant.STUDENT);
        if (!StringUtils.isEmpty(student)) {
            // 学生用户，只能查询自己的信息
            paramMap.put("studentid", student.getId());
        }

        PageBean<Attendance> pageBean = attendanceService.queryPage(paramMap);
        if (!StringUtils.isEmpty(from) && from.equals("combox")) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap<>();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }

    /**
     * 通过选课中的课程id查询学生所选择的课程
     */
    @RequestMapping("/getStudentSelectedCourseList")
    @ResponseBody
    public Object getStudentSelectedCourseList(@RequestParam(value = "studentid", defaultValue = "0") String studentid) {
        // 通过学生id查询选课信息
        List<SelectedCourse> selectedCourseList = selectedCourseService.getAllBySid(Long.valueOf(studentid));
        // 通过选课中的课程id查询学生所选择的课程
        List<Long> ids = new ArrayList<>();
        for (SelectedCourse selectedCourse : selectedCourseList) {
            ids.add(selectedCourse.getCourseId());
        }
        return courseService.getCourseById(ids);
    }


    /**
     * 添加考勤签到
     */
    @PostMapping("/addAttendance")
    @ResponseBody
    public R addAttendance(Attendance attendance) {
        attendance.setDate(DateFormatUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
        // 判断是否已签到
        if (attendanceService.isAttendance(attendance)) {
            // true为已签到
            return R.success("已签到，请勿重复签到");
        } else {
            int count = attendanceService.addAttendance(attendance);
            if (count > 0) {
                return R.success();
            } else {
                return R.fail();
            }
        }
    }

    /**
     * 删除考勤签到
     */
    @PostMapping("/deleteAttendance")
    @ResponseBody
    public R deleteAttendance(Long id) {
        int count = attendanceService.deleteAttendance(id);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }
}
