package com.weizujie.attendance.interceptors;

import com.weizujie.attendance.entity.Admin;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.entity.Teacher;
import com.weizujie.attendance.constants.Constant;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Admin user = (Admin) request.getSession().getAttribute(Constant.ADMIN);
        Teacher teacher = (Teacher) request.getSession().getAttribute(Constant.TEACHER);
        Student student = (Student) request.getSession().getAttribute(Constant.STUDENT);
        if (!StringUtils.isEmpty(user) || !StringUtils.isEmpty(teacher) || !StringUtils.isEmpty(student)) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/system/login");
        return false;
    }

}
