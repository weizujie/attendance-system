package com.weizujie.attendance.controller;

import com.weizujie.attendance.constants.UserConstant;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.service.SelectedCourseService;
import com.weizujie.attendance.service.StudentService;
import com.weizujie.attendance.utils.Data;
import com.weizujie.attendance.utils.PageBean;
import com.weizujie.attendance.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weizujie
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final SelectedCourseService selectedCourseService;

    public StudentController(StudentService studentService, SelectedCourseService selectedCourseService) {
        this.studentService = studentService;
        this.selectedCourseService = selectedCourseService;
    }

    /**
     * 跳转学生列表页面
     */
    @GetMapping("/student_list")
    public String studentList() {
        return "/student/studentList";
    }

    /**
     * 异步加载学生列表
     */
    @RequestMapping("/getStudentList")
    @ResponseBody
    public Object getStudentList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                 String studentName,
                                 @RequestParam(value = "clazzid", defaultValue = "0") String clazzid, String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!StringUtils.isEmpty(studentName)) {
            paramMap.put("username", studentName);
        }
        if (!"0".equals(clazzid)) {
            paramMap.put("clazzid", clazzid);
        }

        //判断是老师还是学生权限
        Student student = (Student) session.getAttribute(UserConstant.STUDENT);
        if (!StringUtils.isEmpty(student)) {
            //是学生权限，只能查询自己的信息
            paramMap.put("studentid", student.getId());
        }

        PageBean<Student> pageBean = studentService.queryPage(paramMap);
        if (!StringUtils.isEmpty(from) && "combox".equals(from)) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap<>();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }

    /**
     * 删除学生
     */
    @PostMapping("/deleteStudent")
    @ResponseBody
    public R deleteStudent(Data data) {
        List<Integer> ids = data.getIds();
        // 判断是否存在课程关联学生
        for (Integer id : ids) {
            if (!selectedCourseService.isStudentId(id)) {
                return R.fail("无法删除,存在课程关联学生");
            }
        }
        int count = studentService.deleteStudent(ids);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }


    /**
     * 添加学生
     */
    @RequestMapping("/addStudent")
    @ResponseBody
    public R addStudent(Student student) {
        int count = studentService.addStudent(student);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    /**
     * 修改学生信息
     */
    @PutMapping("/editStudent")
    @ResponseBody
    public R editStudent(Student student) {
        int i = studentService.updateById(student);
        if (i > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }
}
