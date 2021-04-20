package com.weizujie.attendance.dto;

import lombok.Data;

/**
 * 用户登录参数
 * @author weizujie
 */
@Data
public class LoginDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    private String type;

}

