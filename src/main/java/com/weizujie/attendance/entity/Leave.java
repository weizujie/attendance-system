package com.weizujie.attendance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Leave {

    // 等待审核
    public static int LEAVE_STATUS_WAIT = 0;

    // 同意
    public static int LEAVE_STATUS_AGREE = 1;

    // 不同意
    public static int LEAVE_STATUS_DISAGREE = -1;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long studentId;

    // 请假理由
    private String info;

    // 请假条状态
    private Integer status = LEAVE_STATUS_WAIT;

    // 批复内容
    private String remark;

}
