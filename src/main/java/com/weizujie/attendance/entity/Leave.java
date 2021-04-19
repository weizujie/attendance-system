package com.weizujie.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("s_leave")
public class Leave {

    // 等待审核
    public static int LEAVE_STATUS_WAIT = 0;

    // 同意
    public static int LEAVE_STATUS_AGREE = 1;

    // 不同意
    public static int LEAVE_STATUS_DISAGREE = -1;

    // 请假条 id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 学生 id
    private Integer studentId;

    // 请假理由
    private String info;

    // 请假条状态
    private Integer status = LEAVE_STATUS_WAIT;

    // 批复内容
    private String remark;

    // 请假时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}
