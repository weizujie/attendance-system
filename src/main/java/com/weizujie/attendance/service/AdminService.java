package com.weizujie.attendance.service;

import com.weizujie.attendance.dto.LoginDTO;

/**
 * @author weizujie
 */
public interface AdminService {

    /**
     * 管理员登录
     * @param loginDTO 登录参数
     * @return 管理员信息
     */
    Admin login(LoginDTO loginDTO);

    /**
     * 修改密码
     * @param admin 修改参数
     * @return 影响条数
     */
    int editPswdByAdmin(Admin admin);

    /**
     * 根据用户名获取管理员信息
     * @param username 用户名
     * @return 管理员信息
     */
    Admin getByUsername(String username);
}
