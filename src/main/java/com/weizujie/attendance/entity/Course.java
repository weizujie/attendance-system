package com.weizujie.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Data
@TableName("s_course")
public class Course {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer teacherId;

    // 上课时间
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private Time courseDate;

    // 已选人数
    private Integer selectedNum = 0;

    // 课程最大选课人数
    private Integer maxNum = 50;

    private String info;

}
