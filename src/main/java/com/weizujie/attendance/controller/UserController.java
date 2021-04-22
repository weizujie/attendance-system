package com.weizujie.attendance.controller;

import com.weizujie.attendance.constants.UserConstant;
import com.weizujie.attendance.dto.LoginDTO;
import com.weizujie.attendance.entity.User;
import com.weizujie.attendance.service.SelectedCourseService;
import com.weizujie.attendance.service.UserService;
import com.weizujie.attendance.utils.IdsData;
import com.weizujie.attendance.utils.R;
import com.weizujie.attendance.utils.SaltUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author weizujie
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SelectedCourseService selectedCourseService;

    /**
     * 登录操作
     */
    @PostMapping("/login")
    public R<Boolean> login(LoginDTO loginDTO, HttpSession session) {
        // 空值判断
        if (StringUtils.isEmpty(loginDTO.getUsername()) || StringUtils.isEmpty(loginDTO.getPassword())) {
            return R.fail("用户名或密码不能为空");
        }

        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getUsername(), loginDTO.getPassword());
            subject.login(token);
            // 没有抛异常说明登录成功，获取当前登录用户信息
            User user = userService.getByUsername(loginDTO.getUsername());
            // 将用户信息存到 session 中
            session.setAttribute(UserConstant.LOGIN_USER, user);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            return R.fail("用户名或密码错误");
        }
        return R.success();
    }

    @PostMapping("/add")
    public R<Boolean> addUser(User user) {
        int i = userService.addUser(user);
        if (i > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @PostMapping("/update")
    public R<Boolean> updateUser(User user) {
        int i = userService.updateUser(user);
        if (i > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    /**
     * 修改密码
     */
    @PostMapping("/password")
    public R<Boolean> editPassword(String password, String newPassword, HttpSession session) {
        // 获取当前登录用户
        User user = (User) session.getAttribute(UserConstant.LOGIN_USER);

        // 将输入的原密码加密与数据库中的密码对比
        password = new Md5Hash(password, user.getSalt(), 1024).toString();
        if (!password.equals(user.getPassword())) {
            return R.fail("原密码错误");
        }

        // 修改密码
        String salt = SaltUtil.getSalt(8);
        newPassword = new Md5Hash(newPassword, salt, 1024).toString();
        user.setSalt(salt);
        user.setPassword(newPassword);
        int count = userService.editPwd(user);
        if (count > 0) {
            return R.success("修改成功 ");
        } else {
            return R.fail("修改失败");
        }
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    public R<Boolean> deleteStudent(IdsData data) {
        List<Integer> ids = data.getIds();
        // 若删除的是学生，判断是否存在课程关联
        for (Integer id : ids) {
            if (!selectedCourseService.checkSelectedCourse(id)) {
                return R.fail("无法删除，存在课程关联学生");
            }
        }
        int count = userService.deleteUser(ids);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }
}
