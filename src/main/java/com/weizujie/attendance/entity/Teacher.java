package com.weizujie.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author weizujie
 */
@Data
@TableName("s_teacher")
public class Teacher {

    /**
     * 教师 id 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 教师名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 专业 id
     */
    private Integer clazzId;

    /**
     * 性别 默认：男
     */
    private String sex = "男";

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}
