package com.weizujie.attendance.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author weizujie
 */
@Controller
@RequestMapping("/user")
public class BaseController {

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
        return "/user/index";
    }

    /**
     * 跳转修改密码页面
     */
    @GetMapping("/personalView")
    public String personalView() {
        return "/user/personalView";
    }

    /**
     * 跳转学生列表页面
     */
    @GetMapping("/student_list")
    public String studentList() {
        return "/user/studentList";
    }

    @RequestMapping("/teacher_list")
    public String teacherList() {
        return "/user/teacherList";
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login";
    }

}
