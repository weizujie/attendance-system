package com.weizujie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.weizujie.attendance.dto.LoginDTO;
import com.weizujie.attendance.entity.Admin;
import com.weizujie.attendance.mapper.AdminMapper;
import com.weizujie.attendance.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weizujie
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    /**
     * 管理员登录
     * @param loginDTO 登录参数
     * @return 管理员信息
     */
    @Override
    public Admin login(LoginDTO loginDTO) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, loginDTO.getUsername())
                .eq(Admin::getPassword, loginDTO.getPassword());
        return adminMapper.selectOne(wrapper);
    }

    /**
     * 修改密码
     * @param admin 修改参数
     * @return 影响条数
     */
    @Override
    public int editPswdByAdmin(Admin admin) {
        return adminMapper.updateById(admin);
    }

    /**
     * 根据用户名获取管理员信息
     * @param username 用户名
     * @return 管理员信息
     */
    @Override
    public Admin getByUsername(String username) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, username);
        return adminMapper.selectOne(wrapper);
    }

}
