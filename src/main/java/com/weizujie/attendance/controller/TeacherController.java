package com.weizujie.attendance.controller;

import com.weizujie.attendance.constants.UserConstant;
import com.weizujie.attendance.entity.Teacher;
import com.weizujie.attendance.entity.User;
import com.weizujie.attendance.service.TeacherService;
import com.weizujie.attendance.utils.IdsData;
import com.weizujie.attendance.utils.PageBean;
import com.weizujie.attendance.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weizujie
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    /**
     * 异步加载老师数据列表
     */
    @PostMapping("/list")
    @ResponseBody
    public Object getTeacherList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                 String teacherName,
                                 @RequestParam(value = "clazzid", defaultValue = "0") String clazzid, String from, HttpSession session) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);

        if (!StringUtils.isEmpty(teacherName)) {
            paramMap.put("username", teacherName);
        }

        if (!"0".equals(clazzid)) {
            paramMap.put("clazzid", clazzid);
        }

        // 获取登录用户的用户类型
        User loginUser = (User) session.getAttribute(UserConstant.LOGIN_USER);
        if (UserConstant.TEACHER_CODE.equals(loginUser.getUserType())) {
            // 如果是教师类型，只能查看自己的信息
            paramMap.put("teacherId", loginUser.getId());
        }

        PageBean<Teacher> pageBean = teacherService.queryPage(paramMap);
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
     * 删除教师
     */
    @PostMapping("/deleteTeacher")
    @ResponseBody
    public R<Boolean> deleteTeacher(IdsData data) {
        int count = teacherService.deleteTeacher(data.getIds());
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }
}
