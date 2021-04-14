package com.weizujie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.weizujie.attendance.dto.LoginDTO;
import com.weizujie.attendance.entity.Admin;
import com.weizujie.attendance.mapper.AdminMapper;
import com.weizujie.attendance.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(LoginDTO loginDTO) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, loginDTO.getUsername())
                .eq(Admin::getPassword, loginDTO.getPassword());
        return adminMapper.selectOne(wrapper);
    }

    @Override
    public int editPswdByAdmin(Admin admin) {
        LambdaUpdateWrapper<Admin> wrapper = new LambdaUpdateWrapper<Admin>()
                .set(Admin::getPassword, admin.getPassword())
                .in(Admin::getId, admin.getId());
        return adminMapper.update(admin, wrapper);
    }

}
