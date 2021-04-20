package com.weizujie.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

/**
 * @author weizujie
 */
@Data
@TableName("s_course")
public class Course {

    /**
     * 课程 id 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 教师 id
     */
    private Integer teacherId;

    /**
     * 上课时间
     */
    private String courseDate;

    /**
     * 已选人数
     */
    private Integer selectedNum = 0;

    /**
     * 课程最大选课人数
     */
    private Integer maxNum = 50;

    /**
     * 课程介绍
     */
    private String info;

}
