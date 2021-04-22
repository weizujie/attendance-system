package com.weizujie.attendance.controller;

import com.weizujie.attendance.constants.UserConstant;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.entity.User;
import com.weizujie.attendance.service.SelectedCourseService;
import com.weizujie.attendance.service.StudentService;
import com.weizujie.attendance.service.UserService;
import com.weizujie.attendance.utils.IdsData;
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
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserService userService;

    /**
     * 异步加载学生列表
     */
    @RequestMapping("/list")
    public Object getStudentList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                 @RequestParam(value = "classId", defaultValue = "0") String classId,
                                 String studentName,
                                 String from,
                                 HttpSession session) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);

        if (!StringUtils.isEmpty(studentName)) {
            paramMap.put("username", studentName);
        }

        if (!"0".equals(classId)) {
            paramMap.put("classId", classId);
        }

        User loginUser = (User) session.getAttribute(UserConstant.LOGIN_USER);
        if (UserConstant.STUDENT_CODE.equals(loginUser.getUserType())) {
            // 只能查询自己的信息
            paramMap.put("studentId", loginUser.getId());
        }

        PageBean<User> pageBean = userService.getStudentPage(paramMap);

        if (!StringUtils.isEmpty(from) && "combox".equals(from)) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap<>();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }
}
