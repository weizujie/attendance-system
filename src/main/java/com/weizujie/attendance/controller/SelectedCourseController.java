package com.weizujie.attendance.controller;

import com.weizujie.attendance.constants.Constant;
import com.weizujie.attendance.entity.SelectedCourse;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.service.SelectedCourseService;
import com.weizujie.attendance.utils.R;
import com.weizujie.attendance.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/selectedCourse")
public class SelectedCourseController {

    @Autowired
    private SelectedCourseService selectedCourseService;


    @GetMapping("/selectedCourse_list")
    public String selectedCourseList() {
        return "course/selectedCourseList";
    }

    /**
     * 异步加载选课信息列表
     */
    @PostMapping("/getSelectedCourseList")
    @ResponseBody
    public Object getClazzList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                               @RequestParam(value = "teacherid", defaultValue = "0") String studentid,
                               @RequestParam(value = "teacherid", defaultValue = "0") String courseid, String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!studentid.equals("0")) paramMap.put("studentId", studentid);
        if (!courseid.equals("0")) paramMap.put("courseId", courseid);
        //判断是老师还是学生权限
        Student student = (Student) session.getAttribute(Constant.STUDENT);
        if (!StringUtils.isEmpty(student)) {
            //是学生权限，只能查询自己的信息
            paramMap.put("studentid", student.getId());
        }
        PageBean<SelectedCourse> pageBean = selectedCourseService.queryPage(paramMap);
        if (!StringUtils.isEmpty(from) && from.equals("combox")) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }

    /**
     * 学生进行选课
     */
    @PostMapping("/addSelectedCourse")
    @ResponseBody
    public R addSelectedCourse(SelectedCourse selectedCourse) {
        int count = selectedCourseService.addSelectedCourse(selectedCourse);
        if (count == 1) {
            return R.success();
        } else if (count == 0) {
            return R.fail("选课人数已满");
        } else if (count == 2) {
            return R.fail("已选过这门课程");
        }
        return R.fail("系统繁忙");
    }


    /**
     * 删除选课信息
     */
    @PostMapping("/deleteSelectedCourse")
    @ResponseBody
    public R deleteSelectedCourse(Integer id) {
        int count = selectedCourseService.deleteSelectedCourse(id);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }
}
