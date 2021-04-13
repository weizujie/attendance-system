package com.weizujie.attendance.service;

import com.weizujie.attendance.entity.Admin;

public interface AdminService {

    Admin findByAdmin(Admin admin);

    int editPswdByAdmin(Admin admin);
}
