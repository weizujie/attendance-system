package com.weizujie.attendance.controller;

import com.weizujie.attendance.constants.Constant;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.service.ClazzService;
import com.weizujie.attendance.service.SelectedCourseService;
import com.weizujie.attendance.service.StudentService;
import com.weizujie.attendance.utils.AjaxResult;
import com.weizujie.attendance.utils.Data;
import com.weizujie.attendance.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private SelectedCourseService selectedCourseService;

    /**
     * 跳转学生列表页面
     *
     * @return
     */
    @GetMapping("/student_list")
    public String studentList() {
        return "/student/studentList";
    }

    /**
     * 异步加载学生列表
     *
     * @param page
     * @param rows
     * @param studentName
     * @param clazzid
     * @param from
     * @param session
     * @return
     */
    @RequestMapping("/getStudentList")
    @ResponseBody
    public Object getStudentList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                 String studentName,
                                 @RequestParam(value = "clazzid", defaultValue = "0") String clazzid, String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!StringUtils.isEmpty(studentName)) paramMap.put("username", studentName);
        if (!clazzid.equals("0")) paramMap.put("clazzid", clazzid);

        //判断是老师还是学生权限
        Student student = (Student) session.getAttribute(Constant.STUDENT);
        if (!StringUtils.isEmpty(student)) {
            //是学生权限，只能查询自己的信息
            paramMap.put("studentid", student.getId());
        }

        PageBean<Student> pageBean = studentService.queryPage(paramMap);
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
     * 删除学生
     *
     * @param data
     * @return
     */
    @PostMapping("/deleteStudent")
    @ResponseBody
    public AjaxResult deleteStudent(Data data) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            List<Long> ids = data.getIds();
            Iterator<Long> iterator = ids.iterator();
            while (iterator.hasNext()) {  //判断是否存在课程关联学生
                if (!selectedCourseService.isStudentId(iterator.next())) {
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("无法删除,存在课程关联学生");
                    return ajaxResult;
                }
            }
            int count = studentService.deleteStudent(ids);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("全部删除成功");

            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("删除失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("删除失败");
        }
        return ajaxResult;
    }


    /**
     * 添加学生
     *
     * @param files
     * @param student
     * @return
     * @throws IOException
     */
    @RequestMapping("/addStudent")
    @ResponseBody
    public AjaxResult addStudent(@RequestParam("file") MultipartFile[] files, Student student) throws IOException {

        AjaxResult ajaxResult = new AjaxResult();
        // TODO: 添加学号

        //保存学生信息到数据库
        try {
            int count = studentService.addStudent(student);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("保存成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("保存失败");
        }

        ajaxResult.setSuccess(true);
        return ajaxResult;
    }

    /**
     * 修改学生信息
     *
     * @param files
     * @param student
     * @return
     */
    @PostMapping("/editStudent")
    @ResponseBody
    public AjaxResult editStudent(@RequestParam("file") MultipartFile[] files, Student student) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            int count = studentService.editStudent(student);
            if (count > 0) {
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功");
            } else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }
}
