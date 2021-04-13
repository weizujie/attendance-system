package com.weizujie.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class SelectedCourse {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long studentId;

    private Long courseId;
}
