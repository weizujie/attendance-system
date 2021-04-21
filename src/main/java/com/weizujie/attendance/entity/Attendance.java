package com.weizujie.attendance.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author weizujie
 */
@Data
@TableName("s_attendance")
public class Attendance {

    /**
     * 签到 id 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程 id
     */
    private Integer courseId;

    /**
     * 学生 id
     */
    private Integer studentId;

    /**
     * 签到类型：正常/迟到/补签
     */
    private String type = "正常";

    /**
     * 签到时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;

}
