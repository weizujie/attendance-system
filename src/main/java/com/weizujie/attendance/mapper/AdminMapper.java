package com.weizujie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weizujie.attendance.entity.Admin;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    Admin findByAdmin(Admin admin);

    int editPswdByAdmin(Admin admin);
}
