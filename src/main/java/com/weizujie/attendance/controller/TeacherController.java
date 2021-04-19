package com.weizujie.attendance.controller;

import com.weizujie.attendance.constants.Constant;
import com.weizujie.attendance.entity.Teacher;
import com.weizujie.attendance.service.TeacherService;
import com.weizujie.attendance.utils.R;
import com.weizujie.attendance.utils.Data;
import com.weizujie.attendance.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private HttpSession session;

    @RequestMapping("/teacher_list")
    public String teacherList() {
        return "/teacher/teacherList";
    }

    /**
     * 异步加载老师数据列表
     */
    @PostMapping("/getTeacherList")
    @ResponseBody
    public Object getTeacherList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                 String teacherName,
                                 @RequestParam(value = "clazzid", defaultValue = "0") String clazzid, String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!StringUtils.isEmpty(teacherName)) paramMap.put("username", teacherName);
        if (!clazzid.equals("0")) paramMap.put("clazzid", clazzid);

        //判断是老师还是学生权限
        Teacher teacher = (Teacher) session.getAttribute(Constant.TEACHER);
        if (!StringUtils.isEmpty(teacher)) {
            //是老师权限，只能查询自己的信息
            paramMap.put("teacherid", teacher.getId());
        }

        PageBean<Teacher> pageBean = teacherService.queryPage(paramMap);
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
     * 删除教师
     */
    @PostMapping("/deleteTeacher")
    @ResponseBody
    public R<Boolean> deleteTeacher(Data data) {
        int count = teacherService.deleteTeacher(data.getIds());
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    /**
     * 添加教师
     */
    @RequestMapping("/addTeacher")
    @ResponseBody
    public R<Boolean> addTeacher(Teacher teacher) {
        int count = teacherService.addTeacher(teacher);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @PostMapping("/editTeacher")
    @ResponseBody
    public R<Boolean> editTeacher(Teacher teacher) {
        int count = teacherService.editTeacher(teacher);
        System.out.println(count);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }
}
