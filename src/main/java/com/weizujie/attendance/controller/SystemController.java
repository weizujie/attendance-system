package com.weizujie.attendance.controller;

import com.weizujie.attendance.constants.UserConstant;
import com.weizujie.attendance.dto.LoginDTO;
import com.weizujie.attendance.entity.Admin;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.entity.Teacher;
import com.weizujie.attendance.service.AdminService;
import com.weizujie.attendance.service.StudentService;
import com.weizujie.attendance.service.TeacherService;
import com.weizujie.attendance.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.Objects;

import static com.weizujie.attendance.constants.UserConstant.*;


/**
 * @author weizujie
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    private final AdminService adminService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public SystemController(AdminService adminService, StudentService studentService, TeacherService teacherService) {
        this.adminService = adminService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    /**
     * 跳转登录界面
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    /**
     * 跳转后台主页
     */
    @GetMapping("/index")
    public String index() {
        return "/system/index";
    }

    /**
     * 跳转修改密码页面
     */
    @GetMapping("/personalView")
    public String personalView() {
        return "/system/personalView";
    }

    /**
     * 登录操作
     */
    @PostMapping("/login")
    @ResponseBody
    public R<Boolean> login(LoginDTO loginDTO, HttpSession session) {

        // 空值判断
        if (StringUtils.isEmpty(loginDTO.getUsername()) || StringUtils.isEmpty(loginDTO.getPassword())) {
            return R.fail("用户名或密码不能为空");
        }

        // 用户类型校验
        switch (loginDTO.getType()) {
            case ADMIN_CODE: {
                Admin admin = adminService.login(loginDTO);
                if (Objects.isNull(admin)) {
                    return R.fail("用户名或密码错误");
                }
                session.setAttribute(UserConstant.ADMIN, admin);
                session.setAttribute(UserConstant.USER_TYPE, ADMIN_CODE);
                break;
            }
            case STUDENT_CODE: {
                Student student = studentService.login(loginDTO);
                if (Objects.isNull(student)) {
                    return R.fail("用户名或密码错误");
                }
                session.setAttribute(UserConstant.STUDENT, student);
                session.setAttribute(UserConstant.USER_TYPE, STUDENT_CODE);
                break;
            }
            case TEACHER_CODE: {
                Teacher teacher = teacherService.login(loginDTO);
                if (Objects.isNull(teacher)) {
                    return R.fail("用户名或密码错误");
                }
                session.setAttribute(UserConstant.TEACHER, teacher);
                session.setAttribute(UserConstant.USER_TYPE, TEACHER_CODE);
                break;
            }
            default: return R.fail();
        }
        return R.success("登录成功");
    }

    /**
     * 修改密码
     */
    @PostMapping("/editPassword")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public R<Boolean> editPassword(String password, String newPassword, HttpSession session) {

        // 从 session 中获取用户类型
        String userType = (String) session.getAttribute(UserConstant.USER_TYPE);
        if (Objects.isNull(userType)) {
            return R.fail("用户类型获取失败");
        }

        if (ADMIN_CODE.equals(userType)) {
            // 从 session 中获取管理员信息
            Admin admin = (Admin) session.getAttribute(UserConstant.ADMIN);
            if (Objects.nonNull(admin) && !password.equals(admin.getPassword())) {
                return R.fail("原密码错误");
            }
            admin.setPassword(newPassword);
            int count = adminService.editPswdByAdmin(admin);
            if (count > 0) {
                return R.success();
            } else {
                return R.fail();
            }
        }

        if (STUDENT_CODE.equals(userType)) {
            // 从 session 中获取学生信息
            Student student = (Student) session.getAttribute(UserConstant.STUDENT);
            if (Objects.nonNull(student) && !password.equals(student.getPassword())) {
                return R.fail("原密码错误");
            }
            student.setPassword(newPassword);
            int count = studentService.editPswdByStudent(student);
            if (count > 0) {
                return R.success();
            } else {
                return R.fail();
            }
        }

        if (TEACHER_CODE.equals(userType)) {
            // 从 session 中获取教师信息
            Teacher teacher = (Teacher) session.getAttribute(UserConstant.TEACHER);
            if (Objects.nonNull(teacher) && !password.equals(teacher.getPassword())) {
                return R.fail("原密码错误");
            }
            teacher.setPassword(newPassword);
            int count = teacherService.editPswdByTeacher(teacher);
            if (count > 0) {
                return R.success();
            } else {
                return R.fail();
            }
        }
        return null;
    }


    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }
}
