package com.weizujie.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_attendance")
public class Attendance {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long courseId;

    private Long studentId;

    private String type;

    private String date;

}
