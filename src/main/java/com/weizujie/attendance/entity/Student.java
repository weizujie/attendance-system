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
@TableName("s_student")
public class Student {

    /**
     * 学生id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学号
     */
    private Integer username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String nickName;

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
