package com.weizujie.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author weizujie
 */
@Data
@TableName("s_selected_course")
public class SelectedCourse {

    /**
     * 选课 id 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生 id
     */
    private Integer studentId;

    /**
     * 课程 id
     */
    private Integer courseId;
}
