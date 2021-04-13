package com.weizujie.attendance.service;

import com.weizujie.attendance.entity.Leave;
import com.weizujie.attendance.utils.PageBean;

import java.util.Map;

public interface LeaveService {
    PageBean<Leave> queryPage(Map<String, Object> paramMap);

    int addLeave(Leave leave);

    int editLeave(Leave leave);

    int checkLeave(Leave leave);

    int deleteLeave(Long id);
}
