package com.weizujie.attendance.controller;

import com.weizujie.attendance.constants.Constant;
import com.weizujie.attendance.entity.Admin;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.entity.Teacher;
import com.weizujie.attendance.service.AdminService;
import com.weizujie.attendance.service.StudentService;
import com.weizujie.attendance.service.TeacherService;
import com.weizujie.attendance.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 跳转登录界面
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    /**
     * 登录表单提交 校验
     *
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult submitlogin(String username, String password, String type, HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtils.isEmpty(username)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写用户名");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(password)) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写密码");
            return ajaxResult;
        }

        //数据库校验
        switch (type) {
            case "1": { //管理员
                Admin admin = new Admin();
                admin.setPassword(password);
                admin.setUsername(username);
                Admin ad = adminService.findByAdmin(admin);
                if (StringUtils.isEmpty(ad)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Constant.ADMIN, ad);
                session.setAttribute(Constant.USER_TYPE, "1");
                break;
            }
            case "2": {
                Student student = new Student();
                student.setPassword(password);
                student.setUsername(username);
                Student st = studentService.findByStudent(student);
                if (StringUtils.isEmpty(st)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Constant.STUDENT, st);
                session.setAttribute(Constant.USER_TYPE, "2");
                break;
            }
            case "3": {
                Teacher teacher = new Teacher();
                teacher.setPassword(password);
                teacher.setUsername(username);
                Teacher tr = teacherService.findByTeacher(teacher);
                if (StringUtils.isEmpty(tr)) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Constant.TEACHER, tr);
                session.setAttribute(Constant.USER_TYPE, "3");
                break;
            }
        }
        return ajaxResult;
    }

    /**
     * 跳转后台主页
     */
    @GetMapping("/index")
    public String index() {
        return "/system/index";
    }


    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }

    @GetMapping("/personalView")
    public String personalView() {
        return "/system/personalView";
    }

    /**
     * 修改密码
     */
    @PostMapping("/editPassword")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult editPassword(String password, String newpassword, HttpSession session) {
        AjaxResult ajaxResult = new AjaxResult();
        String usertype = (String) session.getAttribute(Constant.USER_TYPE);
        if (usertype.equals("1")) {
            //管理员
            Admin admin = (Admin) session.getAttribute(Constant.ADMIN);
            if (!password.equals(admin.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            admin.setPassword(newpassword);
            try {
                int count = adminService.editPswdByAdmin(admin);
                if (count > 0) {
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                } else {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        if (usertype.equals("2")) {
            //学生
            Student student = (Student) session.getAttribute(Constant.STUDENT);
            if (!password.equals(student.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            student.setPassword(newpassword);
            try {
                int count = studentService.editPswdByStudent(student);
                if (count > 0) {
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                } else {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        if (usertype.equals("3")) {
            //教师
            Teacher teacher = (Teacher) session.getAttribute(Constant.TEACHER);
            if (!password.equals(teacher.getPassword())) {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            teacher.setPassword(newpassword);
            try {
                int count = teacherService.editPswdByTeacher(teacher);
                if (count > 0) {
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                } else {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        return ajaxResult;
    }

}
