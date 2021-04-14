package com.weizujie.attendance.service;

import com.weizujie.attendance.dto.LoginDTO;
import com.weizujie.attendance.entity.Admin;

public interface AdminService {

    Admin login(LoginDTO loginDTO);

    int editPswdByAdmin(Admin admin);
}
