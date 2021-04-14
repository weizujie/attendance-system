package com.weizujie.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_course")
public class Course {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private Long teacherId;

    private String courseDate;

    // 已选人数
    private Integer selectedNum = 0;

    // 课程最大选课人数
    private Integer maxNum = 50;

    private String info;

}
