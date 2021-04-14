package com.weizujie.attendance.controller;

import com.weizujie.attendance.entity.Leave;
import com.weizujie.attendance.service.LeaveService;
import com.weizujie.attendance.utils.R;
import com.weizujie.attendance.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @RequestMapping("leave_list")
    public String leaveList() {
        return "/leave/leaveList";
    }

    /**
     * 异步加载请假列表
     */
    @PostMapping("/getLeaveList")
    @ResponseBody
    public Object getClazzList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                               @RequestParam(value = "studentid", defaultValue = "0") String studentid,
                               String from) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!studentid.equals("0")) paramMap.put("studentId", studentid);
        PageBean<Leave> pageBean = leaveService.queryPage(paramMap);
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
     * 添加学生请假条
     */
    @PostMapping("/addLeave")
    @ResponseBody
    public R addLeave(Leave leave) {
        int count = leaveService.addLeave(leave);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    /**
     * 修改请假条
     */
    @PostMapping("/editLeave")
    @ResponseBody
    public R editLeave(Leave leave) {
        int count = leaveService.editLeave(leave);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    /**
     * 对假条进行审核
     */
    @PostMapping("/checkLeave")
    @ResponseBody
    public R checkLeave(Leave leave) {
        int count = leaveService.checkLeave(leave);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }


    /**
     * 删除假条
     */
    @PostMapping("/deleteLeave")
    @ResponseBody
    public R deleteLeave(Long id) {
        int count = leaveService.deleteLeave(id);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }
}
