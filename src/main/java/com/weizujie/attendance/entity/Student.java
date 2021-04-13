package com.weizujie.attendance.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Student {

	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	// 学号
	private Integer sn;

	private String username;

	private String password;

	private Long clazzId;

	private String sex = "男";

	private String mobile;
}
